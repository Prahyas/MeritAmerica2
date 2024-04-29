-- 8. The genres of movies that Robert De Niro has appeared in that were released in 2010 or later, sorted alphabetically.
-- (6 rows)

SELECT DISTINCT g.genre_name
FROM person p
JOIN movie_actor ma ON p.person_id = ma.actor_id
JOIN movie_genre mg ON ma.movie_id = mg.movie_id
JOIN genre g ON mg.genre_id = g.genre_id
JOIN movie m ON mg.movie_id = m.movie_id
WHERE p.person_name = 'Robert De Niro'
AND m.release_date >= '2010-01-01'
ORDER BY g.genre_name ASC;
