-- 4. The titles and taglines of all the movies that are in the Fantasy genre.
-- Order the results by title (A-Z).
-- (81 rows)


SELECT m.title, m.tagline
FROM movie m
JOIN movie_genre mg ON m.movie_id = mg.movie_id
JOIN genre g ON mg.genre_id = g.genre_id
WHERE g.genre_name = 'Fantasy'
ORDER BY m.title ASC;
