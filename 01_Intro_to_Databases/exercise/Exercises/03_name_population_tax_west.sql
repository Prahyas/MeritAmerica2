-- 3. The name, population, and sales tax of the states in the "West" census region.
-- Order the results by sales tax, lowest first.
-- (13 rows)
SELECT state_name, population, sales_tax FROM state WHERE census_region = 'West' ORDER BY sales_tax;
