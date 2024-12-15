CREATE TABLE users (
    id BIGSERIAL,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(100), -- Assuming Role is an enum, adjust size as needed
    is_verified BOOLEAN DEFAULT FALSE,
    verification_token VARCHAR(255),
    verification_expires TIMESTAMP,
    reset_password_token VARCHAR(255),
    reset_password_expires TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_user PRIMARY KEY (id)
);


CREATE TABLE property_types (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(255)
);


-- Table for Country
CREATE TABLE countries (
    id BIGSERIAL PRIMARY KEY,
    country_code VARCHAR(10),
    country_name VARCHAR(255)
);

-- Table for Province
CREATE TABLE provinces (
    id BIGSERIAL PRIMARY KEY,
    province_name VARCHAR(255),
    country_id,
    CONSTRAINT fk_province_country FOREIGN KEY (country_id) REFERENCES countries(id)
);

-- Table for City
CREATE TABLE cities (
    id BIGSERIAL PRIMARY KEY,
    city_name VARCHAR(255),
    province_id,
    CONSTRAINT fk_city_province FOREIGN KEY (province_id) REFERENCES provinces(id)
);


-- Table for Property
CREATE TABLE properties (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    property_type_id,
    property_status,
    price NUMERIC(10, 38),
    street VARCHAR(255),
    postal_code VARCHAR(20),
    village VARCHAR(255),
    city_id,
    year_built INTEGER,
    number_of_rooms INTEGER,
    number_of_bathrooms INTEGER,
    number_of_floors INTEGER,
    area NUMERIC(10, 38),
    parking_spaces INTEGER,
    pools INTEGER,
    garages INTEGER,
    security_systems INTEGER,
    elevators INTEGER,
    air_conditionings INTEGER,
    balconies INTEGER,
    storages INTEGER,
    washing_machines INTEGER,
    user_id INTEGER, -- Assuming a reference to a user table
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_property_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_property_city FOREIGN KEY (city_id) REFERENCES cities(id),
    CONSTRAINT fk_property_type FOREIGN KEY (property_type_id) REFERENCES property_types(id)
);

CREATE TABLE property_images (
    id BIGSERIAL PRIMARY KEY,
    image_url VARCHAR(255),
    property_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_property_image_property FOREIGN KEY (property_id) REFERENCES properties(id)
);

CREATE TABLE subscription_plans (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    price NUMERIC(10, 38),
    properties_limit INTEGER,
    duration INTEGER DEFAULT 30,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_subscriptions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    subscription_plan_id BIGINT NOT NULL,
    status VARCHAR(255),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    properties_posted INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_subscription_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_subscription_subscription_plan FOREIGN KEY (subscription_plan_id) REFERENCES subscription_plans(id)
);
