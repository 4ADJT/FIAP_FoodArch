CREATE TABLE assessment (
                            id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            user_id UUID NOT NULL REFERENCES users(id),
                            restaurant_id UUID NOT NULL REFERENCES restaurants(id),
                            comment TEXT,
                            is_like BOOLEAN DEFAULT FALSE,
                            stars INT CHECK (stars BETWEEN 0 AND 5) DEFAULT 0, -- Campo de estrelas de 0 a 5
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_assessment_updated_at
    BEFORE UPDATE ON assessment
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();
