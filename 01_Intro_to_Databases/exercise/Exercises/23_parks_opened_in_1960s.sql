-- 23. The name and date established of parks opened in the 1960s.
-- Order the results by date established, oldest first.
-- (6 rows)

SELECT park_name, date_established
FROM park
WHERE date_established >= '1960-01-01' AND date_established < '1970-01-01'
ORDER BY date_established;