CREATE DATABASE IF NOT EXISTS maxclubcard;

USE maxclubcard;

-- Tabela client
CREATE TABLE IF NOT EXISTS client (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    document VARCHAR(20) NOT NULL UNIQUE,
    birth DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

-- Tabela card
CREATE TABLE IF NOT EXISTS card (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    card_number VARCHAR(20) NOT NULL UNIQUE,
    expiration_date VARCHAR(5) NOT NULL,
    type_card VARCHAR(50) NOT NULL,
    brand_card VARCHAR(50) NOT NULL,
    id_client BIGINT NOT NULL,
    FOREIGN KEY (id_client) REFERENCES client(id)
);

-- Tabela transaction
CREATE TABLE IF NOT EXISTS transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_date DATETIME NOT NULL,
    transaction_number VARCHAR(50) NOT NULL,
    transaction_value DECIMAL(19, 4) NOT NULL,
    id_card BIGINT NOT NULL,
    FOREIGN KEY (id_card) REFERENCES card(id)
);

-- Tabela lucky_number
CREATE TABLE IF NOT EXISTS lucky_number (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lucky_number VARCHAR(36) NOT NULL UNIQUE,
    is_valid BOOLEAN NOT NULL DEFAULT TRUE,
    id_transaction BIGINT NOT NULL,
    FOREIGN KEY (id_transaction) REFERENCES transaction(id)
);

-- Tabela brand
CREATE TABLE IF NOT EXISTS brand (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_name VARCHAR(36) NOT NULL UNIQUE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

-- Inserir um registro na tabela brand, se não existir
INSERT INTO brand (brand_name, is_active) VALUES ('MAXCLUBCARD', true)
    ON DUPLICATE KEY UPDATE brand_name = brand_name;
