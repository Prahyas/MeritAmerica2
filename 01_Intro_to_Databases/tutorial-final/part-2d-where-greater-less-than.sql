SELECT pizza_id, sale_id, size_id, crust, sauce, price, additional_instructions
    FROM pizza
    WHERE price > 10
    ORDER BY price;

SELECT pizza_id, sale_id, size_id, crust, sauce, price, additional_instructions
    FROM pizza
    WHERE price < 10
    ORDER BY price;

SELECT pizza_id, sale_id, size_id, crust, sauce, price, additional_instructions
    FROM pizza
    WHERE price <= 10.99
    ORDER BY price;
