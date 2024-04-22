-- 21. The census region, and the average, minimum, and maximum population of states and districts in each census region. Exclude ones that don't have a census region.
-- Name the population columns 'average_population, 'min_population', and 'max_population'.
-- Order the results from lowest to highest average population.
-- (4 rows)

SELECT census_Region, AVG(population) AS average_population, MIN(population) AS min_population, MAX(population) AS max_population
FROM state
WHERE census_Region IS NOT NULL
GROUP BY census_Region
ORDER BY average_population;