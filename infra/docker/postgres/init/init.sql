DO
$$
BEGIN
    IF
EXISTS (SELECT FROM pg_database WHERE datname = 'sample_book_manager_test') THEN
        PERFORM pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'sample_book_manager_test';
        DROP
DATABASE sample_book_manager_test;
END IF;
END
$$;

CREATE DATABASE sample_book_manager_test
    WITH OWNER = 'test'
    ENCODING = 'UTF8'
    LC_COLLATE = 'ja_JP.UTF-8'
    LC_CTYPE = 'ja_JP.UTF-8'
    TEMPLATE = template0;

\c sample_book_manager;

CREATE TYPE IF NOT EXISTS role_type AS ENUM ('ADMIN', 'USER');

CREATE TABLE IF NOT EXISTS user (
    id SERIAL PRIMARY KEY,
    email VARCHAR(256) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    name VARCHAR(32) NOT NULL,
    role_type role_type
);

CREATE TABLE IF NOT EXISTS book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    author VARCHAR(32) NOT NULL,
    release_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS rental (
    book_id INTEGER NOT NULL PRIMARY KEY REFERENCES book (id),
    user_id INTEGER NOT NULL REFERENCES user (id),
    rental_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    rental_deadline TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

\c sample_book_manager_test;

CREATE TYPE IF NOT EXISTS role_type AS ENUM ('ADMIN', 'USER');

CREATE TABLE IF NOT EXISTS user (
    id SERIAL PRIMARY KEY,
    email VARCHAR(256) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    name VARCHAR(32) NOT NULL,
    role_type role_type
);

CREATE TABLE IF NOT EXISTS book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    author VARCHAR(32) NOT NULL,
    release_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS rental (
    book_id INTEGER NOT NULL PRIMARY KEY REFERENCES book (id),
    user_id INTEGER NOT NULL REFERENCES user (id),
    rental_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    rental_deadline TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
