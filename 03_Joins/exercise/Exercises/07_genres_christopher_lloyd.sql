-- 7. The genres of movies that Christopher Lloyd has appeared in, sorted alphabetically.
-- (8 rows) Hint: DISTINCT will prevent duplicate values in your query results.

SELECT DISTINCT g.genre_name
FROM person p
JOIN movie_actor ma ON p.person_id = ma.actor_id
JOIN movie_genre mg ON ma.movie_id = mg.movie_id
JOIN genre g ON mg.genre_id = g.genre_id
WHERE p.person_name = 'Christopher Lloyd'
ORDER BY g.genre_name ASC;
