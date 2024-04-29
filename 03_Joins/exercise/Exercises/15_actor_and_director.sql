-- 15. The title of the movie and the name of director for movies where the director was also an actor in the same movie.
-- Order the results by movie title (A-Z)
-- (73 rows)

SELECT m.title, p_director.person_name
FROM movie m
JOIN person p_director ON m.director_id = p_director.person_id
JOIN movie_actor ma ON m.movie_id = ma.movie_id
JOIN person p_actor ON ma.actor_id = p_actor.person_id
WHERE p_director.person_id = p_actor.person_id
ORDER BY m.title ASC;
