-- 13. The name, abbreviation, and population of all records in the state table with no official nickname (NULL).
-- Order the results by state name alphabetically.
-- (5 rows)

SELECT state_name, state_abbreviation, population
FROM state
WHERE state_nickname IS NULL
ORDER BY state_name;
