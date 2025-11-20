 CREATE TABLE IF NOT EXISTS producer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS medicine (
    id SERIAL PRIMARY KEY,
    trade_name VARCHAR(255) NOT NULL,
    inn VARCHAR(255) UNIQUE,
    dosage VARCHAR(100),
    form VARCHAR(100),
    producer_id INT REFERENCES producer(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS pharmacy (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    way_from_center VARCHAR(255),
    phone VARCHAR(50),
    working_hours TEXT
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS availability_of_medicine (
    id SERIAL PRIMARY KEY,
    pharmacy_id INT NOT NULL REFERENCES pharmacy(id) ON DELETE CASCADE,
    medicine_id INT NOT NULL REFERENCES medicine(id) ON DELETE CASCADE,
    price DECIMAL(10, 2),
    quantity INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    medicine_id INT NOT NULL REFERENCES medicine(id) ON DELETE CASCADE,
    pharmacy_id INT NOT NULL REFERENCES pharmacy(id) ON DELETE CASCADE,
    quantity INT,
    status VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);