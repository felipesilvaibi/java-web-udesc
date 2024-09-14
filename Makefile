# Makefile para inicializar o banco de dados e a aplicação

.PHONY: help start-db stop-db restart-db build-app start-app stop-app start stop clean

# Variáveis
DOCKER_COMPOSE_FILE=docker-compose.yml

# Alvo padrão
help:
	@echo "Comandos disponíveis:"
	@echo "  make start-db     - Inicia o banco de dados PostgreSQL usando docker compose"
	@echo "  make stop-db      - Para o banco de dados PostgreSQL"
	@echo "  make restart-db   - Reinicia o banco de dados PostgreSQL"
	@echo "  make build-app    - Constrói a imagem Docker da aplicação"
	@echo "  make start-app    - Inicia a aplicação Spring Boot"
	@echo "  make stop-app     - Para a aplicação Spring Boot"
	@echo "  make start        - Inicia o banco de dados e a aplicação"
	@echo "  make stop         - Para o banco de dados e a aplicação"
	@echo "  make clean        - Limpa containers e volumes Docker"

start-db:
	@echo "Iniciando o banco de dados PostgreSQL..."
	docker compose -f $(DOCKER_COMPOSE_FILE) up -d db

stop-db:
	@echo "Parando o banco de dados PostgreSQL..."
	docker compose -f $(DOCKER_COMPOSE_FILE) stop db

restart-db: stop-db start-db

build-app:
	@echo "Construindo a imagem Docker da aplicação..."
	docker compose -f $(DOCKER_COMPOSE_FILE) build app

start-app: build-app
	@echo "Iniciando a aplicação Spring Boot..."
	docker compose -f $(DOCKER_COMPOSE_FILE) up -d app

stop-app:
	@echo "Parando a aplicação Spring Boot..."
	docker compose -f $(DOCKER_COMPOSE_FILE) stop app

start: start-db start-app

stop: stop-app stop-db

clean: stop
	@echo "Removendo containers e volumes Docker..."
	docker compose -f $(DOCKER_COMPOSE_FILE) down -v
	@echo "Removendo arquivos de log..."
	rm -f app.log
