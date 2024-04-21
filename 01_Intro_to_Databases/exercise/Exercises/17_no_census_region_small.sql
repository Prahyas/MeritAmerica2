-- 17. The name, population, and area of all records in the state table with no census region (NULL) and area less than 5000 square kilometers.
-- Order the results by area, largest first.
-- (3 rows)

SELECT state_name, population, area
FROM state
WHERE census_region IS NULL AND area < 5000
ORDER BY area DESC;