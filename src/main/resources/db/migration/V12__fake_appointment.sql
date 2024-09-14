-- Migração para inserir reservas na tabela appointment
DO $$
    DECLARE
        -- Variáveis para os restaurantes e mesas
        selected_restaurants UUID[];
        total_selected INTEGER := 5;
        specific_day DATE := CURRENT_DATE + 1; -- Dia seguinte para reservas completas

        restaurant RECORD;
        table_rec RECORD;

        -- Variáveis para reservas aleatórias
        random_date DATE;
        random_start_time TIME;
        random_end_time TIME;
        random_duration INTERVAL;
        should_reserve BOOLEAN;
        user_id UUID;
    BEGIN
        -- 1. Selecionar aleatoriamente 5 restaurantes para reservas completas
        SELECT ARRAY(
                       SELECT id FROM restaurants
                       ORDER BY RANDOM()
                       LIMIT total_selected
               ) INTO selected_restaurants;

        -- 2. Processar todos os restaurantes
        FOR restaurant IN SELECT id, owner_id FROM restaurants LOOP
                -- Verificar se o restaurante está na lista de reservas completas
                IF restaurant.id = ANY(selected_restaurants) THEN
                    -- Reservar todas as mesas para o dia específico (dia seguinte)
                    FOR table_rec IN SELECT id FROM restaurant_tables WHERE restaurant_id = restaurant.id LOOP
                            -- Definir horário de início
                            random_start_time := TIME '10:00:00';

                            -- Loop para criar reservas de 1 hora até 22:00
                            LOOP
                                -- Calcular horário de término
                                random_end_time := random_start_time + INTERVAL '1 hour';

                                -- Verificar se o horário de término ultrapassa 22:00
                                IF random_end_time > TIME '22:00:00' THEN
                                    EXIT;
                                END IF;

                                -- Selecionar um usuário aleatório que não seja o dono do restaurante
                                SELECT id INTO user_id FROM users
                                WHERE id != restaurant.owner_id
                                ORDER BY RANDOM()
                                LIMIT 1;

                                -- Inserir a reserva, ignorando conflitos de horário
                                BEGIN
                                    INSERT INTO appointment (
                                        restaurant_id,
                                        table_id,
                                        reservation_date,
                                        start_time,
                                        end_time
                                    ) VALUES (
                                                 restaurant.id,
                                                 table_rec.id,
                                                 specific_day,
                                                 random_start_time,
                                                 random_end_time
                                             );
                                EXCEPTION
                                    WHEN unique_violation THEN
                                        -- Ignorar conflitos de horário e continuar
                                        NULL;
                                END;

                                -- Atualizar o horário de início para a próxima reserva
                                random_start_time := random_end_time;
                            END LOOP;
                        END LOOP;
                ELSE
                    -- 3. Reservar algumas mesas aleatoriamente para outros restaurantes
                    FOR table_rec IN SELECT id FROM restaurant_tables WHERE restaurant_id = restaurant.id LOOP
                            -- Decidir aleatoriamente se esta mesa será reservada
                            should_reserve := RANDOM() > 0.5; -- 50% de chance de reservar

                            IF should_reserve THEN
                                -- Gerar uma data de reserva aleatória nos próximos 30 dias
                                random_date := CURRENT_DATE + FLOOR(RANDOM() * 30)::INT;

                                -- Gerar uma duração aleatória de 1 a 2 horas
                                random_duration := (FLOOR(RANDOM() * 2) + 1) * INTERVAL '1 hour';

                                -- Gerar horário de início aleatório entre 10:00 e 20:00
                                random_start_time := TIME '10:00:00' + FLOOR(RANDOM() * 10) * INTERVAL '1 hour'; -- 10h + 0-9 horas

                                -- Calcular horário de término baseado na duração
                                random_end_time := random_start_time + random_duration;

                                -- Garantir que o horário de término não ultrapasse 22:00
                                IF random_end_time > TIME '22:00:00' THEN
                                    random_end_time := TIME '22:00:00';
                                END IF;

                                -- Selecionar um usuário aleatório que não seja o dono do restaurante
                                SELECT id INTO user_id FROM users
                                WHERE id != restaurant.owner_id
                                ORDER BY RANDOM()
                                LIMIT 1;

                                -- Inserir a reserva, ignorando conflitos de horário
                                BEGIN
                                    INSERT INTO appointment (
                                        restaurant_id,
                                        table_id,
                                        reservation_date,
                                        start_time,
                                        end_time
                                    ) VALUES (
                                                 restaurant.id,
                                                 table_rec.id,
                                                 random_date,
                                                 random_start_time,
                                                 random_end_time
                                             );
                                EXCEPTION
                                    WHEN unique_violation THEN
                                        -- Ignorar conflitos de horário e continuar
                                        NULL;
                                END;
                            END IF;
                        END LOOP;
                END IF;
            END LOOP;
    END $$;
