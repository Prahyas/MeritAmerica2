-- 13. The directors of the movies in the "Harry Potter Collection", sorted alphabetically.
-- (4 rows)

SELECT DISTINCT p.person_name
FROM movie m
JOIN collection c ON m.collection_id = c.collection_id
JOIN person p ON m.director_id = p.person_id
WHERE c.collection_name = 'Harry Potter Collection'
ORDER BY person_name ASC;
