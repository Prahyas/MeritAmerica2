# Subqueries and aggregation

The purpose of this exercise is to practice using subqueries and aggregate functions to summarize data using Structured Query Language (SQL).

## Learning objectives

After completing this exercise, you'll understand how to:

* Create nested queries to *send* the results of one query (the subquery) as input into another query (the outer query).
* Use aggregate functions to summarize multiple rows of data.
* Use the `GROUP BY` statement.
* Concatenate strings together in SQL.

## Evaluation criteria and functional requirements

* All of the queries run as expected.
* The number of results returned from your query equals the number of results specified in each question.
* The unit tests pass as expected.
* Code is clean, concise, and readable.

To complete this exercise, you need to write SQL queries in the files that are in the `Exercises` folder. You'll use the `UnitedStates` database as a source for all queries.

In each file, there's a commented out problem statement. Below it, write the query needed to solve the problem. The value immediately after the problem statement is the expected number of rows that the correct SQL query returns. Some of the queries must only return one row and column.

In some files, there's a hint that tells you that the expected value is around a certain number. For example, if your query must return `68117`, the hint reads "Expected answer is around 68,000."

## Getting started

1. If you haven't done so already, create the `UnitedStates` database. The script to do this is `resources/postgresql/UnitedStates.sql` at the top of your repository.
2. Open the `Exercises` folder. The file numbering indicates the suggested completion order, but you can do them in any order you wish.
3. Launch pgAdmin and open each numbered exercise file one-by-one. Write and run the query for the individual exercise. Save the file before moving onto the next exercise.
4. The unit tests project `subqueries-and-aggregation-exercise` is in the same directory as this README. You can open it in IntelliJ and run the tests as you did in earlier exercises.

> Note: Make sure to save your changes to the SQL file before running the unit tests.

## Tips and tricks

* When you need to use a subquery, it's helpful to write the subquery separately first to check that you have the correct SQL and that it returns the correct number of values that you expect. Similarly, you might write the *outer* query with one or more temporary values in place of the subquery to check that it's also correct. Then, when you insert the subquery into the outer query, you have more confidence that your overall query is correct.
* PostgreSQL offers several [aggregate functions][postgresql-aggregate-functions] that are useful for summarizing and grouping data including:
    - **`AVG`** returns the average value of a numeric column
    - **`SUM`**  returns the total sum of a numeric column
    - **`COUNT`** returns the number of rows matching criteria
    - **`MIN`** returns the smallest value of the selected column
    - **`MAX`** returns the largest value of the selected column
* Sometimes when using aggregate functions, you need to use `GROUP BY` to specify the fields to remove duplicate values for. Without the `GROUP BY` clause, you'll only receive a single result row from your query. For instance, to get the total sales for each employee, you might write:
    ```sql
    SELECT employee_id, SUM(sales_amount) AS total_sales
        FROM employee_sale
        GROUP BY employee_id
        ORDER BY employee_id;
    ```
* Remember that you can use the concatenation operator (`||`) to combine multiple values. For instance, to get the full name for employees in a table, you might write:
    ```sql
    SELECT first_name || ' ' || last_name AS employee_name
        FROM employee
        ORDER BY employee_name;
    ```
---

[postgresql-aggregate-functions]: https://www.postgresql.org/docs/12/functions-aggregate.html
