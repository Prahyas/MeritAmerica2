-- 14. The state name, nickname, and census region for states that start with the word "New".
-- Order the results by census region alphabetically, then state nickname alphabetically.
-- (4 rows)

SELECT state_name, state_nickname, census_region
FROM state
WHERE state_name LIKE 'New%'
ORDER BY census_region, state_nickname;