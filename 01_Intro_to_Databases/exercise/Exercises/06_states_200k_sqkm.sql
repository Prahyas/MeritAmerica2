-- 6. The name, abbreviation, population, and area of states with an area greater than 200,000 square kilometers.
-- Order the results by area, highest first.
-- (16 rows)
SELECT state_name, state_abbreviation, population, area
FROM state
WHERE area > 200000
ORDER BY area DESC;
