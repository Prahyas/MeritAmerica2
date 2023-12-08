# Subqueries and aggregation

In this tutorial, you'll write SQL queries that demonstrate:

* How to use subqueries to retrieve data from another table in one query
* How to combine data by summing, counting, and other ways of aggregating it
* How to include aggregated data for subsets within the query results
* How to join strings together in query results

To get started, open the `subqueries-and-aggregation-tutorial.sql` file in pgAdmin. You'll add the queries for this tutorial under the corresponding sections. All these queries use the `PizzaShop` database.

## Part one: Subqueries

Previously, you learned how to use the `WHERE` clause to filter results.

Examine the tables for the PizzaShop database. How can you get all the pizza sales for a customer?

The `sale` table includes a `customer_id`, but what if you don't know the customer's id? Since there isn't other customer information on the `sale` table, you'd have to query the `customer` table for the ID first. To do this, you might use another unique value like the customer's phone or email.

If you know the customer's phone number is 301-728-4087, you can write the following query to get their ID:

```sql
SELECT customer_id
    FROM customer
    WHERE phone_number = '3017284087';
```

Running this gives you the customer's ID of 37, which you can then use to query the `sale` table:

```sql
SELECT sale_id, total, is_delivery, customer_id
    FROM sale
    WHERE customer_id = 37
    ORDER BY sale_id;
```

Using a _subquery_, or nested query, makes this more efficient. It removes a manual step, speeding up the process and eliminating an opportunity for a typo with the ID.

Update the previous query so it uses a subquery in the `WHERE` clause to select the ID for the customer by phone number:

```sql
SELECT sale_id, total, is_delivery, customer_id
    FROM sale
    WHERE customer_id = (
        SELECT customer_id
            FROM customer
            WHERE phone_number = '3017284087'
    )
    ORDER BY sale_id;
```

If you run the two preceding queries, you'll get the same results. Sure, the first one is _shorter_, but it only works if you know the `customer_id`. When a necessary value like an ID is unknown, it's helpful to use a subquery to select it.

In the previous example, the subquery returns a single value to use in a `WHERE`, but what if you needed the sales for all customers living in a particular city?

It's less feasible to manually get the `customer_id` of each customer in a city. However, you can still use a subquery for this.

First, write a query to get all the customer IDs in a city:

```sql
SELECT customer_id
    FROM customer
    WHERE city = 'Mayport'
```

Running this returns multiple rows with the customer IDs. Although you can't use this as a subquery in a `WHERE`, since it expects exactly one value, you *can* use it with a `WHERE IN`:

```sql
SELECT sale_id, total, is_delivery, customer_id
    FROM sale
    WHERE customer_id IN (
        SELECT customer_id
            FROM customer
            WHERE city = 'Mayport'
    )
    ORDER BY sale_id;
```

Now you have all the sales for customers in the city of Mayport.

## Part two: Aggregate functions

To retrieve the combined total of all the shop's sales, write a query with the `SUM` function:

```sql
SELECT SUM(total) AS total_sales
    FROM sale;
```

Run it, and you'll get a sum of 1248.80.

To get the combined total for one customer, add a `WHERE` clause:

```sql
SELECT SUM(total) AS total_sales
    FROM sale
    WHERE customer_id = 37;
```

Now, the query returns the sum of 48.21 from the sales made to the customer with the ID of 37.

Three other aggregate valuable functions when working with numbers are `MIN`, `MAX`, and `AVG`. Write a query that returns the shop's smallest sale, largest sale, and average sale:

```sql
SELECT MIN(total) AS min_sales, MAX(total) AS max_sales, AVG(total) AS avg_sales
    FROM sale;
```

Run it, and you'll learn that the smallest sale is 9.99, the largest is 44.22, and the average is 17.3444444444444444. To round the average to two decimal places, use the `ROUND` function:

```sql
SELECT MIN(total) AS min_sales, MAX(total) AS max_sales, ROUND(AVG(total), 2) AS avg_sales
    FROM sale;
```

Running that query returns an average sale of 17.34.

One more commonly used aggregate function is `COUNT`. Write a query to count how many pizzas had pineapple on them:

```sql
SELECT COUNT(*) AS times_used
    FROM pizza_topping
    WHERE topping_name = 'Pineapple';
```

Run the query, and you'll find the number of pizzas with pineapple is five.

> Remember, it's best practice to use `AS` to give your aggregate values an alias.

## Part three: GROUP BY statements

To find out how many pizzas there were with each of the possible toppings, write a query using `GROUP BY`:

```sql
SELECT topping_name, COUNT(*) AS times_used
    FROM pizza_topping
    GROUP BY topping_name;
```

After running the query, look at the results. Notice that they're either sorted by topping name or not meaningfully sorted.

Add an `ORDER BY` clause to sort them from the most frequently used topping to the least frequently used:

```sql
SELECT topping_name, COUNT(*) AS times_used
    FROM pizza_topping
    GROUP BY topping_name
    ORDER BY times_used DESC;
```

Now, you get the results sorted with Bacon and Tomatoes at the top.

## Part four: String concatenation

The `customer` table contains each customer's first and last names. Write a query that retrieves the names of all the customers in alphabetical order by their last name:

```sql
SELECT first_name, last_name
    FROM customer
    ORDER BY last_name;
```

Running that query returns each customer's first and last names in separate columns. To join them together into a single column, use the `||` operator between the column names. You can use the `||` operator with literal strings too, so you can add a space between the first and last names:

```sql
SELECT first_name || ' ' || last_name AS full_name
FROM customer
ORDER BY last_name;
```

Notice that the results are still sorted by the last name field, even though it no longer appears in its own column.

## Next steps

If you'd like to practice writing more queries that involve grouping and aggregate functions here are a few ideas:

- Find out how many customers provided an email address. (Hint: if you specify a column name in the parentheses after `COUNT` rather than `COUNT(*)`, it counts only the rows with non-null values)
- You can also use `GROUP BY` with multiple columns. Try writing a query for the `pizza` table that groups by crust and sauce.
