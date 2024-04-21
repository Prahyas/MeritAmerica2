-- 5. The name, state abbreviation, and population of the 5 cities with the highest population.
-- (5 rows)
SELECT city_name, state_abbreviation, population FROM city ORDER BY population DESC LIMIT 5;
