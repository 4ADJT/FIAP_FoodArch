-- Função para gerar CPFs válidos
CREATE OR REPLACE FUNCTION generate_valid_cpf() RETURNS VARCHAR AS $$
DECLARE
    digits INTEGER[];
    i INTEGER;
    sum INTEGER;
    result VARCHAR;
BEGIN
    digits := ARRAY[
        FLOOR(RANDOM() * 9)::INTEGER,
        FLOOR(RANDOM() * 9)::INTEGER,
        FLOOR(RANDOM() * 9)::INTEGER,
        FLOOR(RANDOM() * 9)::INTEGER,
        FLOOR(RANDOM() * 9)::INTEGER,
        FLOOR(RANDOM() * 9)::INTEGER,
        FLOOR(RANDOM() * 9)::INTEGER,
        FLOOR(RANDOM() * 9)::INTEGER,
        FLOOR(RANDOM() * 9)::INTEGER
        ];

    -- Primeiro dígito verificador
    sum := 0;
    FOR i IN 1..9 LOOP
            sum := sum + digits[i] * (10 - i);
        END LOOP;
    IF sum % 11 < 2 THEN
        digits[10] := 0;
    ELSE
        digits[10] := 11 - (sum % 11);
    END IF;

    -- Segundo dígito verificador
    sum := 0;
    FOR i IN 1..10 LOOP
            sum := sum + digits[i] * (11 - i);
        END LOOP;
    IF sum % 11 < 2 THEN
        digits[11] := 0;
    ELSE
        digits[11] := 11 - (sum % 11);
    END IF;

    result := '';
    FOR i IN 1..11 LOOP
            result := result || digits[i]::TEXT;
        END LOOP;

    RETURN result;
END;
$$ LANGUAGE plpgsql;

-- Inserir 50 usuários com e-mails únicos, CPFs válidos e datas de nascimento antes de hoje
INSERT INTO users (id, email, cpf, name, birthdate, created_at, updated_at)
SELECT
    uuid_generate_v4(),
    CONCAT('user', seq.seq, '@example.com'),
    generate_valid_cpf(),
    INITCAP(MD5(RANDOM()::TEXT)),
    (CURRENT_DATE - INTERVAL '18 years' - (FLOOR(RANDOM() * (30 * 365)) * INTERVAL '1 day'))::DATE,
    NOW(),
    NOW()
FROM (SELECT generate_series(1, 50) AS seq) seq;
