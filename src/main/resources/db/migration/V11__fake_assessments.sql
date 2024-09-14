-- Criar tabela temporária para armazenar os comentários
CREATE TEMP TABLE temp_comments (comment TEXT);

-- Inserir 150 comentários aleatórios na tabela temporária
INSERT INTO temp_comments (comment) VALUES
                                        ('Great food, amazing service!'),
                                        ('Loved the ambiance, will come again.'),
                                        ('The food was delicious and fresh.'),
                                        ('Friendly staff and quick service.'),
                                        ('Excellent location and beautiful interior.'),
                                        ('The dessert was fantastic!'),
                                        ('Loved the pasta, authentic taste.'),
                                        ('Amazing place for family dinners.'),
                                        ('Great for a quick bite.'),
                                        ('Best pizza in town!'),
                                        ('Nice environment, but the food was just okay.'),
                                        ('Service could be faster.'),
                                        ('Loved the decor and vibe.'),
                                        ('Reasonable prices for the quality.'),
                                        ('The steak was cooked to perfection.'),
                                        ('Highly recommend the sushi rolls.'),
                                        ('Great spot for brunch.'),
                                        ('The drinks were amazing!'),
                                        ('Delicious vegetarian options.'),
                                        ('The wait was a bit long, but worth it.'),
                                        ('Amazing seafood dishes.'),
                                        ('Fantastic service, will come back for sure.'),
                                        ('The cocktails were top notch.'),
                                        ('The burger was one of the best I’ve ever had.'),
                                        ('Great place to take the kids.'),
                                        ('Beautiful presentation of the dishes.'),
                                        ('Best pasta I have ever had.'),
                                        ('Would not recommend the dessert.'),
                                        ('Nice ambiance but average food.'),
                                        ('Loved the wine selection.'),
                                        ('A great place for a date night.'),
                                        ('Will definitely return.'),
                                        ('Could use more vegan options.'),
                                        ('The soup was bland.'),
                                        ('Good for large groups.'),
                                        ('The appetizers were great.'),
                                        ('Best steakhouse in town.'),
                                        ('Enjoyed the outdoor seating.'),
                                        ('Not worth the hype.'),
                                        ('Friendly but slow service.'),
                                        ('Nice spot, but a bit overrated.'),
                                        ('Great for business meetings.'),
                                        ('The chef is very talented.'),
                                        ('Loved the spicy options.'),
                                        ('Best seafood I’ve had in a long time.'),
                                        ('The salad was fresh and crisp.'),
                                        ('Loved the atmosphere.'),
                                        ('Perfect for a family dinner.'),
                                        ('Would visit again for the drinks.'),
                                        ('Loved the unique dishes.'),
                                        ('The staff was rude.'),
                                        ('A delightful experience overall.'),
                                        ('The food is inconsistent.'),
                                        ('Perfect for special occasions.'),
                                        ('Had a great time with friends.'),
                                        ('The dessert menu is a must-try.'),
                                        ('Good for a quick lunch.'),
                                        ('A bit too pricey for what you get.'),
                                        ('Couldn’t stop eating the fries.'),
                                        ('Had to wait a long time for a table.'),
                                        ('Perfect for date night.'),
                                        ('Great ambiance, food was just okay.'),
                                        ('Loved the spicy chicken.'),
                                        ('Great wine list.'),
                                        ('Amazing vegan options.'),
                                        ('Best breakfast spot around.'),
                                        ('The place was clean and well-kept.'),
                                        ('Loved the cozy vibe.'),
                                        ('Great service but food was lacking.'),
                                        ('Loved the outdoor area.'),
                                        ('Perfect place to unwind.'),
                                        ('Service with a smile.'),
                                        ('Loved the steak.'),
                                        ('Food was good, but overpriced.'),
                                        ('Loved the decor and setup.'),
                                        ('Delicious and filling meals.'),
                                        ('Service was outstanding.'),
                                        ('The seafood platter was amazing.'),
                                        ('Loved the open kitchen concept.'),
                                        ('The dessert was too sweet.'),
                                        ('A lovely place to dine.'),
                                        ('Great spot for a romantic evening.'),
                                        ('Loved the appetizers.'),
                                        ('Best place to celebrate.'),
                                        ('Loved the fusion dishes.'),
                                        ('A must-visit for foodies.'),
                                        ('Great service, food was average.'),
                                        ('Perfect for lunch meetings.'),
                                        ('Nice selection of desserts.'),
                                        ('Loved the decor.'),
                                        ('Friendly staff but slow service.'),
                                        ('Perfect for family outings.'),
                                        ('Loved the seafood options.'),
                                        ('Nice setting, average food.'),
                                        ('Would recommend the steak.'),
                                        ('Great place to relax.'),
                                        ('The service was top-notch.'),
                                        ('Loved the dessert selection.'),
                                        ('Great for casual dining.'),
                                        ('Loved the outdoor seating area.'),
                                        ('The sushi was fresh and delicious.'),
                                        ('Nice place but noisy.'),
                                        ('Loved the drinks menu.'),
                                        ('Great place for breakfast.'),
                                        ('The food was overcooked.'),
                                        ('A lovely spot for dinner.'),
                                        ('Nice view but average food.'),
                                        ('Loved the wine selection.'),
                                        ('Perfect for a quick meal.'),
                                        ('Loved the tapas menu.'),
                                        ('Great selection of wines.'),
                                        ('The place was cozy and warm.'),
                                        ('Loved the small plates.'),
                                        ('Perfect for a dinner date.'),
                                        ('Nice vibe but overpriced.'),
                                        ('Loved the cocktails.'),
                                        ('Would recommend the dessert.'),
                                        ('Nice place for a casual lunch.'),
                                        ('Great for special occasions.'),
                                        ('Loved the open-air seating.'),
                                        ('The service was great, but the food was cold.'),
                                        ('Perfect place for brunch.'),
                                        ('The food was overpriced.'),
                                        ('Loved the pizza.'),
                                        ('Great spot for a quick coffee.'),
                                        ('The food was disappointing.'),
                                        ('Loved the ambiance, food was just okay.'),
                                        ('Perfect for a weekend brunch.'),
                                        ('Nice place but food was average.'),
                                        ('Loved the breakfast options.'),
                                        ('Great service but the food was bland.'),
                                        ('Perfect for lunch with friends.'),
                                        ('Nice setting but food was mediocre.'),
                                        ('Loved the sushi.'),
                                        ('The food was a bit salty.'),
                                        ('Great for dinner with family.'),
                                        ('The food was not worth the price.'),
                                        ('Loved the outdoor dining.'),
                                        ('The dessert was too rich.'),
                                        ('Great for a romantic dinner.'),
                                        ('Loved the vegetarian options.'),
                                        ('Perfect spot for a date.'),
                                        ('Nice ambiance but food was average.'),
                                        ('Loved the decor.'),
                                        ('The place was too loud.'),
                                        ('Great for a quick lunch.'),
                                        ('Perfect for a family dinner.'),
                                        ('The drinks were fantastic.'),
                                        ('Nice spot for brunch.'),
                                        ('Great place for dinner.'),
                                        ('The food was amazing, but service was slow.'),
                                        ('Perfect for a date night.'),
                                        ('Loved the outdoor seating.'),
                                        ('Great service, food was just okay.'),
                                        ('The food was delicious.'),
                                        ('The place was cozy and warm.'),
                                        ('Loved the outdoor area.'),
                                        ('Great for a quick bite.'),
                                        ('Nice place.');

-- Identificar todos os restaurantes e seus donos
DO $$
    DECLARE
        r RECORD;
        c RECORD;
        u UUID;
        comment_count INT := 150; -- Total de comentários disponíveis
        restaurant_count INT := 36; -- Número de restaurantes
        comments_per_restaurant INT := comment_count / restaurant_count; -- Média de comentários por restaurante
    BEGIN
        -- Calcular a quantidade de comentários por restaurante (4 cada um para cobrir os 150)
        IF comment_count % restaurant_count > 0 THEN
            comments_per_restaurant := comments_per_restaurant + 1; -- Alguns restaurantes receberão 5
        END IF;

        -- Selecionar todos os restaurantes e distribuir os comentários igualmente
        FOR r IN SELECT id, owner_id FROM restaurants LOOP
                FOR i IN 1..comments_per_restaurant LOOP
                        -- Selecionar um comentário aleatório da tabela temporária
                        SELECT * FROM temp_comments ORDER BY RANDOM() LIMIT 1 INTO c;

                        -- Selecionar um usuário aleatório que não seja o dono do restaurante
                        SELECT id FROM users WHERE id != r.owner_id ORDER BY RANDOM() LIMIT 1 INTO u;

                        -- Inserir a avaliação usando o comentário e o usuário selecionado, apenas se ambos forem encontrados
                        IF c.comment IS NOT NULL AND u IS NOT NULL THEN
                            INSERT INTO assessment (id, user_id, restaurant_id, comment, is_like, stars, created_at, updated_at)
                            VALUES (
                                       uuid_generate_v4(),
                                       u,
                                       r.id,
                                       c.comment,
                                       (CASE WHEN RANDOM() > 0.5 THEN TRUE ELSE FALSE END),  -- Define 'is_like' como TRUE para 50%
                                       FLOOR(RANDOM() * 6),  -- Define estrelas de 0 a 5
                                       NOW(),
                                       NOW()
                                   );

                            -- Remover o comentário da tabela temporária para que não seja reutilizado
                            DELETE FROM temp_comments WHERE comment = c.comment;
                        END IF;
                    END LOOP;
            END LOOP;
    END $$;

-- Limpeza da tabela temporária após o uso
DROP TABLE IF EXISTS temp_comments;
