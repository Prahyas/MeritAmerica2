-- 16. The names and birthdays of actors born in the 1950s who acted in movies that were released in 1985.
-- Order the results by actor from oldest to youngest.
-- (20 rows)

SELECT DISTINCT p.person_name, p.birthday
FROM person p
JOIN movie_actor ma ON p.person_id = ma.actor_id
JOIN movie m ON ma.movie_id = m.movie_id
WHERE EXTRACT(YEAR FROM p.birthday) BETWEEN 1950 AND 1959
AND EXTRACT(YEAR FROM m.release_date) = 1985
ORDER BY p.birthday ASC;
