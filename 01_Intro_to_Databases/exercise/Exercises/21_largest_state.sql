-- 21. The name and nickname for the largest state.
-- (1 row)

SELECT state_name, state_nickname
FROM state
ORDER BY area DESC
LIMIT 1;
