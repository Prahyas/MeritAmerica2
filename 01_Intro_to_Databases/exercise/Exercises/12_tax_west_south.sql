-- 12. The state name, nickname, and sales tax from records in the state table in the "West" and "South" census regions with a sales tax that isn't 0%.
-- Order the results by sales tax (highest first), then state name alphabetically (A-Z).
-- (26 rows)

SELECT state_name, state_nickname, sales_tax
FROM state
WHERE (census_region = 'West' OR census_region = 'South') AND sales_Tax != 0
 ORDER BY sales_tax DESC, state_name;