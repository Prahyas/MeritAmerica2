-- 2. The name and area of all cities in Pennsylvania (PA).
-- Order the results in reverse alphabetical order (Z-A) by city name.
-- (4 rows)
SELECT city_name, area from city WHERE state_abbreviation = 'PA' ORDER BY city_name DESC;
