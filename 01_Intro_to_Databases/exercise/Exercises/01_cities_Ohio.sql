-- 1. The name and population of all cities in Ohio (OH).
-- Order the results alphabetically (A-Z) by city.
-- (6 rows)
SELECT city_name, population from city WHERE state_abbreviation = 'OH' ORDER BY city_name;

