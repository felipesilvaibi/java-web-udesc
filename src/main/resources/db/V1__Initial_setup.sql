-- DROP TABLES IF EXISTS (Esses comandos são comentados por segurança)
-- DROP TABLE IF EXISTS public.tb_movie_actor;
-- DROP TABLE IF EXISTS public.tb_actor;
-- DROP TABLE IF EXISTS public.tb_movie;
-- DROP TABLE IF EXISTS public.tb_customer;

-- Criação da tabela tb_customer
CREATE TABLE public.tb_customer (
	id uuid NOT NULL,
	address varchar(64) NOT NULL,
	birth_date varchar(10) NOT NULL,
	cpf varchar(11) NOT NULL,
	created_at timestamp(6) NOT NULL,
	email varchar(64) NOT NULL,
	"name" varchar(32) NOT NULL,
	phone varchar(11) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	CONSTRAINT tb_customer_pkey PRIMARY KEY (id),
	CONSTRAINT uk1r2x5kcodv7u39r4k8jfk78b8 UNIQUE (cpf),
	CONSTRAINT uka9xeibptr987g1od1c430m1w9 UNIQUE (email),
	CONSTRAINT ukf8602v7whiicyob59sabtu00u UNIQUE (phone)
);

-- Criação da tabela tb_movie
CREATE TABLE public.tb_movie (
	id uuid NOT NULL,
	created_at timestamp(6) NOT NULL,
	duration int4 NOT NULL,
	genre varchar(64) NOT NULL,
	synopsis varchar(512) NOT NULL,
	title varchar(128) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	CONSTRAINT tb_movie_pkey PRIMARY KEY (id)
);

-- Criação da tabela tb_actor
CREATE TABLE public.tb_actor (
	id uuid NOT NULL,
	created_at timestamp(6) NOT NULL,
	"name" varchar(32) NOT NULL,
	nationality varchar(32) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	CONSTRAINT tb_actor_pkey PRIMARY KEY (id)
);

-- Criação da tabela tb_movie_actor com delete cascade
CREATE TABLE public.tb_movie_actor (
    id uuid NOT NULL,
    created_at timestamp(6) NOT NULL,
    updated_at timestamp(6) NOT NULL,
    actor_id uuid,
    movie_id uuid,
    CONSTRAINT tb_movie_actor_pkey PRIMARY KEY (id),
    CONSTRAINT uko2gmiyt6f7arr7mw1a50h644p UNIQUE (movie_id, actor_id),
    CONSTRAINT fkkc11oso89pvbyrwpg265bmj9v FOREIGN KEY (movie_id) REFERENCES public.tb_movie(id) ON DELETE CASCADE,
    CONSTRAINT fknaiva749sdfsiyo4xsj3xajqi FOREIGN KEY (actor_id) REFERENCES public.tb_actor(id)
);
