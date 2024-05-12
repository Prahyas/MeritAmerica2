package com.techelevator.movies.dao;

import com.techelevator.movies.model.Movie;
import com.techelevator.movies.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcPersonDao implements PersonDao {
    private final String PERSON_SELECT = "SELECT p.person_id, p.person_name, p.birthday, p.deathday, p.biography, p.profile_path, p.home_page from person p ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcPersonDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Person> getPersons() {
        String sql = PERSON_SELECT;

        List<Person> persons = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Person singlePerson = mapRowToPerson(results);
            persons.add(singlePerson);
        }

        return persons;
    }

    @Override
    public Person getPersonById(int id) {
        String sql = PERSON_SELECT + "WHERE p.person_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            return mapRowToPerson(results);
        } else {
            return null;
        }

    }

    @Override
    public List<Person> getPersonsByName(String name, boolean useWildCard) {
        String sql = PERSON_SELECT + "WHERE LOWER(p.person_name) LIKE ?;";

        if (useWildCard) {
            name = "%" + name.toLowerCase() + "%";
        } else {
            name = name.toLowerCase();
        }


        List<Person> persons = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);

        while (results.next()) {
            Person person = mapRowToPerson(results);
            persons.add(person);
        }

        return persons;

    }

    @Override
    public List<Person> getPersonsByCollectionName(String collectionName, boolean useWildCard) {
        String sql = PERSON_SELECT +
                "JOIN movie_actor ma ON ma.actor_id = p.person_id " +
                "JOIN movie m ON m.movie_id = ma.movie_id " +
                "JOIN collection c ON c.collection_id = m.collection_id " +
                "WHERE LOWER(c.collection_name) LIKE ?;";

        if (useWildCard) {
            collectionName = "%" + collectionName.toLowerCase() + "%";
        } else {
            collectionName = collectionName.toLowerCase();
        }


        List<Person> persons = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionName);

        while (results.next()) {
            Person person = mapRowToPerson(results);
            persons.add(person);
        }

        return persons;
    }
    private Person mapRowToPerson(SqlRowSet results) {
        Person person = new Person();
        person.setId(results.getInt("person_id"));
        person.setName(results.getString("person_name"));
        person.setBirthday(results.getDate("birthday") != null ? results.getDate("birthday").toLocalDate() : null);
        person.setDeathDate(results.getDate("deathday") != null ? results.getDate("deathday").toLocalDate() : null);
        person.setBiography(results.getString("biography"));
        person.setProfilePath(results.getString("profile_path"));
        person.setHomePage(results.getString("home_page"));
        return person;
    }
}
