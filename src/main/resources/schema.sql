-- Таблица для пунктов выдачи заказов
CREATE TABLE IF NOT EXISTS pvz (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
    );

-- Таблица для приемок
CREATE TABLE IF NOT EXISTS reception (
    id UUID PRIMARY KEY,
    reception_date TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    pvz_id UUID NOT NULL,
    FOREIGN KEY (pvz_id) REFERENCES pvz(id)
    );

-- Таблица для пользователей (если нужна)
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
    );