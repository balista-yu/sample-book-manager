DO
$$
BEGIN
    IF
EXISTS (SELECT FROM pg_database WHERE datname = 'sample_book_manager') THEN
        PERFORM pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'sample_book_manager';
        DROP
DATABASE sample_book_manager;
END IF;
END
$$;

CREATE DATABASE sample_book_manager
    WITH OWNER = 'test'
    ENCODING = 'UTF8'
    LC_COLLATE = 'ja_JP.UTF-8'
    LC_CTYPE = 'ja_JP.UTF-8'
    TEMPLATE = template0;

DO
$$
BEGIN
    IF
EXISTS (SELECT FROM pg_database WHERE datname = 'sample_book_manager_other') THEN
        PERFORM pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'sample_book_manager_other';
        DROP
DATABASE sample_book_manager_other;
END IF;
END
$$;

CREATE DATABASE sample_book_manager_other
    WITH OWNER = 'test'
    ENCODING = 'UTF8'
    LC_COLLATE = 'ja_JP.UTF-8'
    LC_CTYPE = 'ja_JP.UTF-8'
    TEMPLATE = template0;

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

DO
$$
BEGIN
    IF
EXISTS (SELECT FROM pg_database WHERE datname = 'sample_book_manager_other_test') THEN
        PERFORM pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'sample_book_manager_other_test';
        DROP
DATABASE sample_book_manager_other_test;
END IF;
END
$$;

CREATE DATABASE sample_book_manager_other_test
    WITH OWNER = 'test'
    ENCODING = 'UTF8'
    LC_COLLATE = 'ja_JP.UTF-8'
    LC_CTYPE = 'ja_JP.UTF-8'
    TEMPLATE = template0;

\c sample_book_manager;

DO
$$
BEGIN
    IF
NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'role_type') THEN
    CREATE TYPE role_type AS ENUM ('ADMIN', 'GENERAL');
END IF;
END
$$;

CREATE TABLE IF NOT EXISTS operator (
    id SERIAL PRIMARY KEY,
    email VARCHAR(256) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    name VARCHAR(32) NOT NULL,
    role_type role_type
);

CREATE TABLE IF NOT EXISTS book (
    id CHAR(26) COLLATE "C" PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    author VARCHAR(32) NOT NULL,
    release_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS rental (
    book_id CHAR(26) COLLATE "C" NOT NULL PRIMARY KEY REFERENCES book (id),
    operator_id INTEGER NOT NULL REFERENCES operator (id),
    rental_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    return_deadline TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO operator (email, password, name, role_type) VALUES ('test@example.com', '$2y$05$7IWY4KIY8l52R5XbTfU24uRw679eZeHHiSwtY2sfT5R7eAVGdJ1IS', 'test', 'ADMIN');

\c sample_book_manager_other;

CREATE TABLE IF NOT EXISTS book (
    id CHAR(26) COLLATE "C" PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    author VARCHAR(32) NOT NULL,
    release_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

\c sample_book_manager_test;

DO
$$
BEGIN
    IF
NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'role_type') THEN
    CREATE TYPE role_type AS ENUM ('ADMIN', 'GENERAL');
END IF;
END
$$;

CREATE TABLE IF NOT EXISTS operator (
    id SERIAL PRIMARY KEY,
    email VARCHAR(256) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    name VARCHAR(32) NOT NULL,
    role_type role_type
);

CREATE TABLE IF NOT EXISTS book (
    id CHAR(26) COLLATE "C" PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    author VARCHAR(32) NOT NULL,
    release_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS rental (
    book_id CHAR(26) COLLATE "C" NOT NULL PRIMARY KEY REFERENCES book (id),
    operator_id INTEGER NOT NULL REFERENCES operator (id),
    rental_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    return_deadline TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

\c sample_book_manager_other_test;

CREATE TABLE IF NOT EXISTS book (
    id CHAR(26) COLLATE "C" PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    author VARCHAR(32) NOT NULL,
    release_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
