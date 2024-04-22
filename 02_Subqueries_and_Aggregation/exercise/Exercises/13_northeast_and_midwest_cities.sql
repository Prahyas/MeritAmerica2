-- 13. The city name, state abbreviation, and population for all cities in the Northeast and Midwest census regions.
-- Order the results by state abbreviation first (alphabetical), then by population (largest first).
-- (84 rows)

SELECT city_name, state_abbreviation, population
FROM city
WHERE state_abbreviation IN (
SELECT state_abbreviation
FROM state
WHERE census_region = 'Northeast' OR census_region = 'Midwest'
)
ORDER BY state_abbreviation, population DESC;