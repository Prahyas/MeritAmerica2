-- 19. The genre name and the number of movies in each genre. Name the count column 'num_of_movies'.
-- Order the results from the highest movie count to the lowest.
-- (19 rows, the highest expected count is around 400).

SELECT g.genre_name, COUNT(m.movie_id) AS num_of_movies
FROM genre g
LEFT JOIN movie_genre mg ON g.genre_id = mg.genre_id
LEFT JOIN movie m ON mg.movie_id = m.movie_id
GROUP BY g.genre_name
ORDER BY num_of_movies DESC;
