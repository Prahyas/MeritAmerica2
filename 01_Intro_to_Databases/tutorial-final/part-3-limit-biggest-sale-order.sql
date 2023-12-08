SELECT sale_id, total, is_delivery, customer_id
    FROM sale
    ORDER BY total DESC
    LIMIT 1;
