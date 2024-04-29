-- 14. The names of actors who've appeared in the movies in the "Back to the Future Collection", sorted alphabetically.
-- (28 rows)

SELECT DISTINCT p.person_name
FROM movie m
JOIN collection c ON m.collection_id = c.collection_id
JOIN movie_actor ma ON m.movie_id = ma.movie_id
JOIN person p ON ma.actor_id = p.person_id
WHERE c.collection_name = 'Back to the Future Collection'
ORDER BY person_name ASC;
