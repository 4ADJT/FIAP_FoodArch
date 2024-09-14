-- Inserir endereços para cada restaurante existente
INSERT INTO addresses (
    id,
    restaurant_id,
    street,
    number,
    neighborhood,
    city,
    state,
    complement,
    country,
    zip_code,
    created_at,
    updated_at
)
SELECT
    uuid_generate_v4(),                              -- Gera um ID único para o endereço
    r.id,                                            -- ID do restaurante
    CONCAT('Rua ', FLOOR(RANDOM() * 1000 + 1)),      -- Gera um nome de rua fictício
    FLOOR(RANDOM() * 100 + 1)::TEXT,                 -- Gera um número aleatório para o endereço
    CONCAT('Bairro ', FLOOR(RANDOM() * 100 + 1)),    -- Gera um bairro fictício
    'São Paulo',                                     -- Cidade padrão (ajuste conforme necessário)
    'SP',                                            -- Estado padrão (ajuste conforme necessário)
    CASE WHEN RANDOM() > 0.5 THEN 'Apto ' || FLOOR(RANDOM() * 100) ELSE NULL END, -- Complemento opcional
    'Brasil',                                        -- País padrão
    LPAD((FLOOR(RANDOM() * 90000 + 10000))::TEXT, 5, '0') || '-' || LPAD((FLOOR(RANDOM() * 900 + 100))::TEXT, 3, '0'), -- Gera um CEP aleatório
    NOW(),                                           -- Data de criação
    NOW()                                            -- Data de atualização
FROM
    restaurants r;