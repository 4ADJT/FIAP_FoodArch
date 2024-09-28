CREATE OR REPLACE VIEW restaurant_data_view AS
WITH
    kitchens_agg AS (
        SELECT
            rk.restaurant_id,
            ARRAY_AGG(k.name) AS kitchens
        FROM restaurant_kitchens rk
                 JOIN kitchens k ON rk.kitchen_id = k.id
        GROUP BY rk.restaurant_id
    ),
    operating_hours_agg AS (
        SELECT
            oh.restaurant_id,
            JSON_AGG(
                    JSON_BUILD_OBJECT(
                            'day_of_week', oh.day_of_week,
                            'open_time', oh.open_time,
                            'close_time', oh.close_time
                    )
                    ORDER BY oh.day_of_week
            ) AS operating_hours
        FROM operating_hours oh
        GROUP BY oh.restaurant_id
    ),
    tables_agg AS (
        SELECT
            rt.restaurant_id,
            JSON_AGG(
                    JSON_BUILD_OBJECT(
                            'table_id', rt.id,
                            'table_number', rt.table_number,
                            'available', rt.is_available
                    )
                    ORDER BY rt.table_number
            ) AS tables
        FROM restaurant_tables rt
        GROUP BY rt.restaurant_id
    )
SELECT
    r.id AS restaurant_id,
    r.owner_id AS user_id,
    u.name AS user_name,
    r.name AS restaurant_name,
    a.street AS address_street,
    a.number AS address_number,
    a.neighborhood AS address_neighborhood,
    a.city AS address_city,
    a.state AS address_state,
    a.complement AS address_complement,
    a.country AS address_country,
    a.zip_code AS address_zipcode,
    COALESCE(ka.kitchens, ARRAY[]::text[]) AS kitchens,
    COALESCE(oh.operating_hours, '[]'::json) AS operation,
    COALESCE(ta.tables, '[]'::json) AS tables
FROM restaurants r
         INNER JOIN users u ON r.owner_id = u.id
         LEFT JOIN addresses a ON r.id = a.restaurant_id
         LEFT JOIN kitchens_agg ka ON r.id = ka.restaurant_id
         LEFT JOIN operating_hours_agg oh ON r.id = oh.restaurant_id
         LEFT JOIN tables_agg ta ON r.id = ta.restaurant_id;
