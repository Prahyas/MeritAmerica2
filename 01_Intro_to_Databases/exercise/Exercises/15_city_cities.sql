-- 15. The name, state abbreviation, and population for cities that end with the word "City".
-- Order the results by population, largest first.
-- (11 rows)

SELECT city_name, state_abbreviation, population
FROM city
WHERE city_name LIKE '%City'
ORDER BY population DESC;