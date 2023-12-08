-- Part one: Inner joins

---- Joining pizza and size tables

SELECT pizza_id, size_id, crust, sauce
    FROM pizza
    ORDER BY pizza_id;

SELECT size_id, size_description, diameter
    FROM size
    ORDER BY diameter;

SELECT pizza_id, size.size_id, size_description, diameter, crust, sauce
    FROM pizza
    JOIN size ON pizza.size_id = size.size_id
    ORDER BY pizza_id;

---- Joining sale and customer tables

SELECT sale_id, customer_id, total
    FROM sale
    ORDER BY sale_id;

SELECT customer_id, first_name, last_name
    FROM customer
    ORDER BY customer_id;

SELECT sale_id, customer.customer_id, total, first_name, last_name
    FROM sale
    JOIN customer ON sale.customer_id = customer.customer_id
    ORDER BY sale_id;

---- Joining pizza, pizza_topping, and topping tables

SELECT p.pizza_id, sale_id, size_id, price, topping_name
    FROM pizza p
    JOIN pizza_topping pt ON p.pizza_id = pt.pizza_id
    ORDER BY p.pizza_id;

SELECT pizza_id, t.topping_name, additional_price
    FROM topping t
    JOIN pizza_topping pt ON t.topping_name = pt.topping_name
    ORDER BY pizza_id;

SELECT p.pizza_id, sale_id, size_id, price, t.topping_name, additional_price
    FROM pizza p
    JOIN pizza_topping pt ON p.pizza_id = pt.pizza_id
    JOIN topping t ON t.topping_name = pt.topping_name
    ORDER BY p.pizza_id;


-- Part two: Outer joins

---- LEFT

SELECT sale_id, total, first_name, last_name
    FROM sale s
    LEFT JOIN customer c ON s.customer_id = c.customer_id
    ORDER BY sale_id;

---- RIGHT

SELECT sale_id, total, first_name, last_name
    FROM customer c
    RIGHT JOIN sale s ON s.customer_id = c.customer_id
    ORDER BY sale_id;

SELECT sale_id, total, first_name, last_name
    FROM customer c
    LEFT JOIN sale s ON s.customer_id = c.customer_id
    ORDER BY sale_id;
--or
SELECT sale_id, total, first_name, last_name
    FROM sale s
    RIGHT JOIN customer c ON s.customer_id = c.customer_id
    ORDER BY sale_id;
