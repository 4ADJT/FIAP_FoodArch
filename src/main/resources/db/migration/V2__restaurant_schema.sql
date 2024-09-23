CREATE TABLE kitchens (
                          id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          name VARCHAR(255) NOT NULL UNIQUE,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO kitchens (name) VALUES
                                ('Cozinha Italiana'),
                                ('Cozinha Francesa'),
                                ('Cozinha Japonesa'),
                                ('Cozinha Chinesa'),
                                ('Cozinha Brasileira'),
                                ('Cozinha Mexicana'),
                                ('Cozinha Tailandesa'),
                                ('Cozinha Indiana'),
                                ('Cozinha Mediterrânea'),
                                ('Cozinha Espanhola'),
                                ('Cozinha Árabe'),
                                ('Cozinha Americana'),
                                ('Cozinha Fusion'),
                                ('Cozinha Vegana/Vegetariana'),
                                ('Cozinha Coreana'),
                                ('Cozinha Peruana'),
                                ('Outras Cozinhas');

CREATE TABLE restaurants (
                             id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                             name VARCHAR(255) NOT NULL,
                             owner_id UUID NOT NULL REFERENCES users(id),
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE restaurant_kitchens (
                                     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                     restaurant_id UUID NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
                                     kitchen_id UUID NOT NULL REFERENCES kitchens(id),
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     UNIQUE (restaurant_id, kitchen_id)
);

CREATE TYPE day_of_week_enum AS ENUM (
    'SUNDAY',
    'MONDAY',
    'TUESDAY',
    'WEDNESDAY',
    'THURSDAY',
    'FRIDAY',
    'SATURDAY'
    );

CREATE TABLE operating_hours (
                                 id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                 restaurant_id UUID NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
                                 day_of_week day_of_week_enum NOT NULL,
                                 open_time TIME NOT NULL,
                                 close_time TIME NOT NULL,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 UNIQUE (restaurant_id, day_of_week)
);

CREATE TABLE restaurant_tables (
                                   id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                   restaurant_id UUID NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
                                   table_number INTEGER NOT NULL,
                                   is_available BOOLEAN NOT NULL DEFAULT TRUE,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   UNIQUE (restaurant_id, table_number)
);

CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_kitchens_updated_at
    BEFORE UPDATE ON kitchens
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_restaurants_updated_at
    BEFORE UPDATE ON restaurants
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_restaurant_kitchens_updated_at
    BEFORE UPDATE ON restaurant_kitchens
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_operating_hours_updated_at
    BEFORE UPDATE ON operating_hours
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_restaurant_tables_updated_at
    BEFORE UPDATE ON restaurant_tables
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();
