package br.com.fiap.foodarch.infra.gateways.persistance.restaurants;

import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import br.com.fiap.foodarch.infra.external.restaurants.mv.RestaurantDataMV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {

  @Query("SELECT r FROM RestaurantEntity r WHERE r.owner.id = :ownerId")
  Page<RestaurantEntity> findByOwnerId(UUID ownerId, Pageable pageable);

  @Query(value = """
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
        r.id AS RESTAURANT_ID,
        r.owner_id AS USER_ID,
        u.name AS USER_NAME,
        r.name AS RESTAURANT_NAME,
        a.street AS ADDRESS_STREET,
        a.number AS ADDRESS_NUMBER,
        a.neighborhood AS ADDRESS_NEIGHBORHOOD,
        a.city AS ADDRESS_CITY,
        a.state AS ADDRESS_STATE,
        a.complement AS ADDRESS_COMPLEMENT,
        a.country AS ADDRESS_COUNTRY,
        a.zip_code AS ADDRESS_ZIPCODE,
        COALESCE(ka.kitchens, ARRAY[]::text[]) AS KITCHENS,
        COALESCE(oh.operating_hours, '[]'::json) AS OPERATION,
        COALESCE(ta.tables, '[]'::json) AS TABLES
    FROM restaurants r
    INNER JOIN users u ON r.owner_id = u.id
    LEFT JOIN addresses a ON r.id = a.restaurant_id
    LEFT JOIN kitchens_agg ka ON r.id = ka.restaurant_id
    LEFT JOIN operating_hours_agg oh ON r.id = oh.restaurant_id
    LEFT JOIN tables_agg ta ON r.id = ta.restaurant_id
    WHERE (:restaurantId IS NULL OR r.id = :restaurantId)
    """, nativeQuery = true)
  List<RestaurantDataMV> findRestaurantDataMV(@Param("restaurantId") UUID restaurantId);

}
