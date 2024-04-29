-- 6. The genres of "The Wizard of Oz" sorted in alphabetical order (A-Z).
-- (3 rows)

SELECT g.genre_name
FROM genre g
JOIN movie_genre mg ON g.genre_id = mg.genre_id
JOIN movie m ON mg.movie_id = m.movie_id
WHERE m.title = 'The Wizard of Oz'
ORDER BY g.genre_name ASC;
