CREATE INDEX idx_restaurants_owner_id ON restaurants(owner_id);

CREATE INDEX idx_restaurant_kitchens_kitchen_id ON restaurant_kitchens(kitchen_id);

CREATE INDEX idx_assessment_restaurant_id ON assessment(restaurant_id);
CREATE INDEX idx_assessment_user_id ON assessment(user_id);
CREATE INDEX idx_assessment_user_restaurant ON assessment(user_id, restaurant_id);

CREATE INDEX idx_appointment_restaurant_date ON appointment(restaurant_id, reservation_date);

CREATE INDEX idx_restaurant_tables_restaurant_id ON restaurant_tables(restaurant_id);
CREATE INDEX idx_restaurant_tables_is_available ON restaurant_tables(is_available);

CREATE INDEX idx_operating_hours_restaurant_id ON operating_hours(restaurant_id);

CREATE INDEX idx_addresses_city ON addresses(city);
CREATE INDEX idx_addresses_zip_code ON addresses(zip_code);
