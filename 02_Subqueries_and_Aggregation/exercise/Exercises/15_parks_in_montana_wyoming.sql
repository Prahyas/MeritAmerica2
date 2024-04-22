-- 15. The park name, date established, and area for parks in Montana and Wyoming.
-- Order the results by park name alphabetically.
-- (3 rows)

SELECT park_name, date_established, area
FROM park
WHERE park_id IN (
SELECT park_id
FROM park_state
WHERE state_abbreviation = 'MT' OR state_abbreviation = 'WY'
)
ORDER BY park_name;

