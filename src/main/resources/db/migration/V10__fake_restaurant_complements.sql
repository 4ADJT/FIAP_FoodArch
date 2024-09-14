-- Inserir mesas para cada restaurante com quantidades aleatórias entre 5 e 20 mesas
INSERT INTO restaurant_tables (id, restaurant_id, table_number, is_available, created_at, updated_at)
SELECT
    uuid_generate_v4(),
    r.id,
    row_number() OVER (PARTITION BY r.id ORDER BY random()) AS table_number,
    TRUE,
    NOW(),
    NOW()
FROM restaurants r
         JOIN (SELECT * FROM generate_series(1, 20)) AS g ON TRUE
WHERE (SELECT FLOOR(RANDOM() * 16) + 5) > g.generate_series;

-- Inserir horários de funcionamento para cada restaurante, com horários aleatórios por dia da semana
INSERT INTO operating_hours (id, restaurant_id, day_of_week, open_time, close_time, created_at, updated_at)
SELECT
    uuid_generate_v4(),
    r.id,
    dow.day_of_week::day_of_week,  -- Casting para o tipo correto do campo
    (CASE WHEN RANDOM() > 0.5 THEN '08:00:00' ELSE '09:00:00' END)::TIME,
    (CASE WHEN RANDOM() > 0.5 THEN '18:00:00' ELSE '20:00:00' END)::TIME,
    NOW(),
    NOW()
FROM restaurants r,
     (VALUES ('Sunday'::day_of_week), ('Monday'::day_of_week), ('Tuesday'::day_of_week),
             ('Wednesday'::day_of_week), ('Thursday'::day_of_week), ('Friday'::day_of_week),
             ('Saturday'::day_of_week)) AS dow(day_of_week);

-- Inserir cozinhas aleatórias para cada restaurante, com pelo menos uma cozinha e no máximo três
INSERT INTO restaurant_kitchens (id, restaurant_id, kitchen_id, created_at, updated_at)
SELECT
    uuid_generate_v4(),
    r.id,
    k.id,
    NOW(),
    NOW()
FROM restaurants r
         JOIN (
    SELECT id
    FROM kitchens
    ORDER BY RANDOM()
    LIMIT (FLOOR(RANDOM() * 3) + 1)  -- Limita entre 1 e 3 cozinhas
) k ON TRUE;
