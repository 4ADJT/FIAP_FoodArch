CREATE TABLE addresses (
                           id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                           restaurant_id UUID NOT NULL UNIQUE REFERENCES restaurants(id),
                           street VARCHAR(255) NOT NULL,
                           number VARCHAR(50) NOT NULL,
                           neighborhood VARCHAR(255) NOT NULL,
                           city VARCHAR(255) NOT NULL,
                           state VARCHAR(255) NOT NULL,
                           complement VARCHAR(255),
                           country VARCHAR(255) NOT NULL,
                           zip_code VARCHAR(20) NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_addresses_updated_at
    BEFORE UPDATE ON addresses
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();
