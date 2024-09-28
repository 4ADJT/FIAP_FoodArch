CREATE MATERIALIZED VIEW restaurant_data_mv AS
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

CREATE UNIQUE INDEX restaurant_data_mv_idx ON restaurant_data_mv (restaurant_id);

CREATE OR REPLACE FUNCTION refresh_restaurant_data_mv_concurrently()
    RETURNS TRIGGER AS $$
BEGIN
    REFRESH MATERIALIZED VIEW CONCURRENTLY restaurant_data_mv;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER refresh_restaurant_data_mv_after_insert_restaurants
    AFTER INSERT ON restaurants
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_update_restaurants
    AFTER UPDATE ON restaurants
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_delete_restaurants
    AFTER DELETE ON restaurants
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_insert_users
    AFTER INSERT ON users
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_update_users
    AFTER UPDATE ON users
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_delete_users
    AFTER DELETE ON users
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_insert_addresses
    AFTER INSERT ON addresses
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_update_addresses
    AFTER UPDATE ON addresses
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_delete_addresses
    AFTER DELETE ON addresses
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_insert_restaurant_kitchens
    AFTER INSERT ON restaurant_kitchens
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_update_restaurant_kitchens
    AFTER UPDATE ON restaurant_kitchens
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_delete_restaurant_kitchens
    AFTER DELETE ON restaurant_kitchens
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_insert_kitchens
    AFTER INSERT ON kitchens
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_update_kitchens
    AFTER UPDATE ON kitchens
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_delete_kitchens
    AFTER DELETE ON kitchens
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_insert_operating_hours
    AFTER INSERT ON operating_hours
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_update_operating_hours
    AFTER UPDATE ON operating_hours
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_delete_operating_hours
    AFTER DELETE ON operating_hours
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_insert_restaurant_tables
    AFTER INSERT ON restaurant_tables
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_update_restaurant_tables
    AFTER UPDATE ON restaurant_tables
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();

CREATE TRIGGER refresh_restaurant_data_mv_after_delete_restaurant_tables
    AFTER DELETE ON restaurant_tables
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_restaurant_data_mv_concurrently();
