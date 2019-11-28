WITH RECURSIVE r AS (
    SELECT id, parent_id, name, dtype
    FROM fias_fias_entity
    WHERE parent_id = ?parentId
    UNION
    SELECT fias_fias_entity.id, fias_fias_entity.parent_id, fias_fias_entity.name, fias_fias_entity.dtype
    FROM fias_fias_entity
        JOIN r ON fias_fias_entity.parent_id = r.id AND (fias_fias_entity.dtype = 'fias$City' OR fias_fias_entity.dtype = 'fias$Location'))

SELECT id, name FROM r
    WHERE r.dtype = 'fias$City' OR r.dtype = 'fias$Location'
    ORDER BY r.name;