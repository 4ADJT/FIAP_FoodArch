-- 1. Selecionar 27 usuários
WITH selected_users AS (
    SELECT id AS user_id
    FROM users
    ORDER BY RANDOM()
    LIMIT 27
),

-- 2. Criar um restaurante para cada um dos 27 usuários
     restaurants_for_27 AS (
         INSERT INTO restaurants (id, name, owner_id, created_at, updated_at)
             SELECT
                 uuid_generate_v4(),
                 'Restaurante ' || ROW_NUMBER() OVER ()::text,
                 su.user_id,
                 NOW(),
                 NOW()
             FROM selected_users su
             RETURNING id, owner_id
     ),

-- 3. Selecionar 6 usuários dos 27 e criar mais um restaurante para cada um
     restaurants_for_6 AS (
         INSERT INTO restaurants (id, name, owner_id, created_at, updated_at)
             SELECT
                 uuid_generate_v4(),
                 'Restaurante Extra ' || ROW_NUMBER() OVER ()::text,
                 rf27.owner_id,
                 NOW(),
                 NOW()
             FROM restaurants_for_27 rf27
             ORDER BY RANDOM()
             LIMIT 6
             RETURNING id, owner_id
     )

-- 4. Selecionar 3 usuários dos 6 e criar mais um restaurante para cada um
INSERT INTO restaurants (id, name, owner_id, created_at, updated_at)
SELECT
    uuid_generate_v4(),
    'Restaurante Extra 2 ' || ROW_NUMBER() OVER ()::text,
    rf6.owner_id,
    NOW(),
    NOW()
FROM restaurants_for_6 rf6
ORDER BY RANDOM()
LIMIT 3;
