-- 9. The name, abbreviation, population, and sales tax of all states and territories with a sales tax greater than 6.6%.
-- Order the results by sales tax (lowest first), then by state name alphabetically (A-Z).
-- (9 rows)
SELECT state_name, state_abbreviation, population, sales_tax
FROM state
WHERE sales_tax > 6.6
ORDER BY sales_tax, state_name;

