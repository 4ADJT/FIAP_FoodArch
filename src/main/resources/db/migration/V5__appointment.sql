-- Ativar a extensão necessária para as restrições de exclusão
CREATE EXTENSION IF NOT EXISTS btree_gist;

-- Criar a tabela appointment com as restrições apropriadas
CREATE TABLE IF NOT EXISTS appointment (
                                           id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                           restaurant_id UUID NOT NULL REFERENCES restaurants(id),
                                           table_id UUID NOT NULL REFERENCES restaurant_tables(id),
                                           reservation_date DATE NOT NULL,
                                           start_time TIME NOT NULL,
                                           end_time TIME NOT NULL,
                                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           CONSTRAINT chk_time_valid CHECK (end_time > start_time),
                                           EXCLUDE USING GIST (
                                               table_id WITH =,
                                               tsrange(
                                                       (reservation_date + start_time)::timestamp,
                                                       (reservation_date + end_time)::timestamp,
                                                       '[)'
                                               ) WITH &&
                                               )
);


CREATE TRIGGER update_appointment_updated_at
    BEFORE UPDATE ON appointment
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();
