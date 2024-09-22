DROP TABLE IF EXISTS tb_rental;
DROP TABLE IF EXISTS tb_stock;
DROP TABLE IF EXISTS tb_movie_actor;
DROP TABLE IF EXISTS tb_movie;
DROP TABLE IF EXISTS tb_actor;
DROP TABLE IF EXISTS tb_customer;

-- Criação da tabela tb_actor
CREATE TABLE tb_actor (
    id UUID PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    nationality VARCHAR(32) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Criação da tabela tb_customer
CREATE TABLE tb_customer (
    id UUID PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(64) NOT NULL UNIQUE,
    phone VARCHAR(11) NOT NULL UNIQUE,
    address VARCHAR(64) NOT NULL,
    birth_date VARCHAR(10) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Criação da tabela tb_movie
CREATE TABLE tb_movie (
    id UUID PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    genre VARCHAR(64) NOT NULL,
    synopsis VARCHAR(512) NOT NULL,
    duration INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Criação da tabela tb_stock
CREATE TABLE tb_stock (
    id UUID PRIMARY KEY,
    movie_id UUID NOT NULL UNIQUE,
    quantity INTEGER NOT NULL,
    rented INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES tb_movie(id) ON DELETE CASCADE
);

-- Criação da tabela tb_movie_actor
CREATE TABLE tb_movie_actor (
    id UUID PRIMARY KEY,
    movie_id UUID NOT NULL,
    actor_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    UNIQUE (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES tb_movie(id) ON DELETE CASCADE,
    FOREIGN KEY (actor_id) REFERENCES tb_actor(id) ON DELETE CASCADE
);

-- Criação da tabela tb_rental
CREATE TABLE tb_rental (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    stock_id UUID NOT NULL,
    rental_date DATE NOT NULL,
    return_date DATE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES tb_customer(id),
    FOREIGN KEY (stock_id) REFERENCES tb_stock(id)
);
