package com.techelevator.projects.dao;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Department;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcDepartmentDao implements DepartmentDao {

    private final String DEPARTMENT_SELECT = "SELECT d.department_id, d.name FROM department d ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcDepartmentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Department getDepartmentById(int id) {
        Department department = null;
        String sql = DEPARTMENT_SELECT +
                " WHERE d.department_id=?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                department = mapRowToDepartment(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }


        return department;
    }

    @Override
    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = DEPARTMENT_SELECT;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                departments.add(mapRowToDepartment(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return departments;
    }

    @Override
    public Department createDepartment(Department department) {
        Department newDepartment = null; // declare return value

        String sql = "INSERT INTO department (name) " +
                "VALUES (?) RETURNING department_id;";

        try {
            int newDepartmentId = jdbcTemplate.queryForObject(sql, int.class, department.getName());

            // retrieve the new author to get any updated fields like timestamps
            newDepartment = getDepartmentById(newDepartmentId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newDepartment;
    }

    @Override
    public Department updateDepartment(Department department) {
        Department updatedDepartment = null; // declare return value

        String sql = "UPDATE department SET name = ? " +
                "WHERE department_id = ?;";

        try {
            int numberOfRows = jdbcTemplate.update(sql, department.getName(),
                    department.getId());

            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                // retrieve the updated author to get any updated fields like timestamps
                updatedDepartment = getDepartmentById(department.getId());
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedDepartment;
    }

    @Override
    public int deleteDepartmentById(int id) {
        int numberOfRows = 0;
        String deleteProjectEmployeeSql = "DELETE FROM project_employee WHERE employee_id IN (SELECT employee_id FROM employee WHERE department_id = ?);";
        String deleteEmployeeSql = "DELETE FROM employee WHERE department_id = ?;";
        String deleteDepartmentSql = "DELETE FROM department WHERE department_id = ?;";

        try {
            jdbcTemplate.update(deleteProjectEmployeeSql, id);
            jdbcTemplate.update(deleteEmployeeSql, id);
            numberOfRows = jdbcTemplate.update(deleteDepartmentSql, id);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return numberOfRows;
    }

    private Department mapRowToDepartment(SqlRowSet results) {
        Department department = new Department();
        department.setId(results.getInt("department_id"));
        department.setName(results.getString("name"));
        return department;
    }

}
