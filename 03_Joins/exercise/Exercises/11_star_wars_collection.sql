-- 11. The titles of the movies in the "Star Wars Collection" ordered by release date, most recent first.
-- (9 rows)

SELECT m.title
FROM movie m
JOIN collection c ON m.collection_id = c.collection_id
WHERE c.collection_name = 'Star Wars Collection'
ORDER BY m.release_date DESC;
