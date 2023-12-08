-- *** Part one: Subqueries ***

-- Get customer_id from phone
SELECT customer_id
    FROM customer
    WHERE phone_number = '3017284087';

-- Get sales by using the customer id
SELECT sale_id, total, is_delivery, customer_id
    FROM sale
    WHERE customer_id = 37
    ORDER BY sale_id;

-- Get sales for customer by phone using subquery
SELECT sale_id, total, is_delivery, customer_id
    FROM sale
    WHERE customer_id = (
        SELECT customer_id
            FROM customer
            WHERE phone_number = '3017284087'
    )
    ORDER BY sale_id;

-- Get customer ids for city of Mayport
SELECT customer_id
    FROM customer
    WHERE city = 'Mayport'

-- Use subquery to get all sales for customers in Mayport
SELECT sale_id, total, is_delivery, customer_id
    FROM sale
    WHERE customer_id IN (
        SELECT customer_id
            FROM customer
            WHERE city = 'Mayport'
    )
    ORDER BY sale_id;

-- *** Part two: Aggregate functions ***

-- Get sum of all sales
SELECT SUM(total) AS total_sales
    FROM sale;

-- Get sum of all sales for a customer
SELECT SUM(total) AS total_sales
    FROM sale
    WHERE customer_id = 37;

-- Get the smallest, largest, and average sale amount
SELECT MIN(total) AS min_sales, MAX(total) AS max_sales, AVG(total) AS avg_sales
    FROM sale;

-- Round the average sale to 2 decimal places
SELECT MIN(total) AS min_sales, MAX(total) AS max_sales, round(AVG(total), 2) AS avg_sales
    FROM sale;

-- Count the number of pizzas with pineapple
SELECT COUNT(*) AS times_used
    FROM pizza_topping
    WHERE topping_name = 'Pineapple';

-- *** Part three: GROUP BY ***

-- Get the number of pizzas with each topping
SELECT topping_name, COUNT(*) AS times_used
    FROM pizza_topping
    GROUP BY topping_name;

-- Sort them from most frequently used to least
SELECT topping_name, COUNT(*) AS times_used
    FROM pizza_topping
    GROUP BY topping_name
    ORDER BY times_used DESC;

-- *** Part four: String concatenation ***

-- Get all customer names alphabetically by last name
SELECT first_name, last_name
    FROM customer
    ORDER BY last_name;

-- Concatenate the first and last names with a space between
SELECT first_name || ' ' || last_name AS full_name
    FROM customer
    ORDER BY last_name;
