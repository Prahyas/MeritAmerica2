-- 2. The names and birthdays of actors in "The Fifth Element"
-- Order the results alphabetically (A-Z) by name.
-- (15 rows)

SELECT p.person_name, p.birthday
FROM movie_actor ma
JOIN movie m ON ma.movie_id = m.movie_id
JOIN person p ON ma.actor_id = p.person_id
WHERE m.title = 'The Fifth Element'
ORDER BY p.person_name ASC;