-- Função para gerar CPFs válidos
CREATE OR REPLACE FUNCTION generate_valid_cpf(formatted INTEGER DEFAULT 0)
    RETURNS VARCHAR AS
$$
DECLARE
    n1 INTEGER;
    n2 INTEGER;
    n3 INTEGER;
    n4 INTEGER;
    n5 INTEGER;
    n6 INTEGER;
    n7 INTEGER;
    n8 INTEGER;
    n9 INTEGER;
    d1 INTEGER;
    d2 INTEGER;
    cpf VARCHAR(14);
BEGIN
    LOOP
        -- Gerar os 9 primeiros dígitos aleatórios (0 a 9)
        n1 := FLOOR(RANDOM() * 10)::INTEGER;
        n2 := FLOOR(RANDOM() * 10)::INTEGER;
        n3 := FLOOR(RANDOM() * 10)::INTEGER;
        n4 := FLOOR(RANDOM() * 10)::INTEGER;
        n5 := FLOOR(RANDOM() * 10)::INTEGER;
        n6 := FLOOR(RANDOM() * 10)::INTEGER;
        n7 := FLOOR(RANDOM() * 10)::INTEGER;
        n8 := FLOOR(RANDOM() * 10)::INTEGER;
        n9 := FLOOR(RANDOM() * 10)::INTEGER;

        -- Verificar se todos os dígitos são iguais
        IF n1 = n2 AND n2 = n3 AND n3 = n4 AND n4 = n5 AND n5 = n6 AND n6 = n7 AND n7 = n8 AND n8 = n9 THEN
            CONTINUE; -- Regerar os dígitos
        END IF;

        -- Cálculo do primeiro dígito verificador (d1)
        d1 := n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;
        d1 := 11 - (d1 % 11);
        IF d1 >= 10 THEN
            d1 := 0;
        END IF;

        -- Cálculo do segundo dígito verificador (d2)
        d2 := d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;
        d2 := 11 - (d2 % 11);
        IF d2 >= 10 THEN
            d2 := 0;
        END IF;

        -- Verificar novamente se todos os dígitos (incluindo os verificadores) não são iguais
        IF d1 = n1 AND d2 = n1 THEN
            CONTINUE; -- Regerar os dígitos
        END IF;

        -- Construir o CPF final com ou sem formatação
        IF formatted = 1 THEN
            cpf :=
                    n1::VARCHAR || '.' ||
                    n2::VARCHAR || '.' ||
                    n3::VARCHAR || '.' ||
                    n4::VARCHAR || '.' ||
                    n5::VARCHAR || '.' ||
                    n6::VARCHAR || '.' ||
                    n7::VARCHAR || '.' ||
                    n8::VARCHAR || '.' ||
                    n9::VARCHAR || '-' ||
                    d1::VARCHAR || d2::VARCHAR;
        ELSE
            cpf :=
                    n1::VARCHAR ||
                    n2::VARCHAR ||
                    n3::VARCHAR ||
                    n4::VARCHAR ||
                    n5::VARCHAR ||
                    n6::VARCHAR ||
                    n7::VARCHAR ||
                    n8::VARCHAR ||
                    n9::VARCHAR ||
                    d1::VARCHAR ||
                    d2::VARCHAR;
        END IF;

        RETURN cpf;
    END LOOP;
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
