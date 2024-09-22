# Projeto Aplicação Web com Spring Boot e PostgreSQL

Este projeto é uma aplicação web desenvolvida em Java utilizando o framework Spring Boot. A aplicação simula um sistema de locadora de filmes, permitindo gerenciar clientes, atores, filmes, estoque e locações. Utiliza o Spring Data JPA para interagir com um banco de dados PostgreSQL e está containerizada usando Docker e Docker Compose para facilitar a implantação e execução. O projeto inclui um **Makefile** para simplificar a execução de comandos comuns.

## Índice

*   [Descrição do Projeto](#descri%C3%A7%C3%A3o-do-projeto)

*   [Pré-requisitos](#pr%C3%A9-requisitos)

*   [Tecnologias Utilizadas](#tecnologias-utilizadas)

*   [Instalação e Configuração](#instala%C3%A7%C3%A3o-e-configura%C3%A7%C3%A3o)

    *   [Clonar o Repositório](#clonar-o-reposit%C3%B3rio)

*   [Construção e Execução](#constru%C3%A7%C3%A3o-e-execu%C3%A7%C3%A3o)

    *   [Comandos Principais](#comandos-principais)

*   [Interagindo com a API](#interagindo-com-a-api)

    *   [Endpoints Disponíveis](#endpoints-dispon%C3%ADveis)

    *   [Exemplos de Requisições](#exemplos-de-requisi%C3%A7%C3%B5es)

*   [Parando a Aplicação](#parando-a-aplica%C3%A7%C3%A3o)

*   [Limpeza de Artefatos](#limpeza-de-artefatos)

*   [Observações](#observa%C3%A7%C3%B5es)

*   [Licença](#licen%C3%A7a)

## Descrição do Projeto

A aplicação é um sistema de locadora de filmes que permite:

*   **Gerenciar Clientes**: Criar, listar, atualizar e remover clientes.

*   **Gerenciar Atores**: Adicionar e associar atores aos filmes.

*   **Gerenciar Filmes**: Criar filmes com informações detalhadas e associar atores.

*   **Gerenciar Estoque**: Controlar a quantidade de cópias disponíveis de cada filme.

*   **Gerenciar Locações**: Registrar locações de filmes pelos clientes e controlar devoluções.

A aplicação expõe uma API RESTful que pode ser consumida por um cliente frontend ou por ferramentas como Postman e cURL.

## Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:

*   **Java 17** ou superior

*   **Maven 3.6+**

*   **Docker**

*   **Docker Compose**

*   **Make** (para utilizar o Makefile)

## Tecnologias Utilizadas

*   **Java 17**

*   **Spring Boot 3**

*   **Spring Data JPA**

*   **PostgreSQL**

*   **Docker e Docker Compose**

## Instalação e Configuração

### Clonar o Repositório

Clone o repositório para sua máquina local:

```
git clone https://github.com/felipesilvaibi/java-web-udesc.git
cd java-web-udesc
```

## Construção e Execução

O projeto inclui um **Makefile** que simplifica a execução de comandos comuns para construção e execução da aplicação.

### Comandos Principais

*   **Iniciar o banco de dados**:

    ```
    make start-db
    ```

    Inicia o serviço do PostgreSQL usando Docker Compose.

*   **Construir a imagem da aplicação**:

    ```
    make build-app
    ```

    Compila a aplicação e constrói a imagem Docker.

*   **Iniciar a aplicação**:

    ```
    make start-app
    ```

    Inicia o serviço da aplicação Spring Boot.

*   **Iniciar banco de dados e aplicação**:

    ```
    make start
    ```

    Inicia ambos os serviços.

*   **Parar a aplicação**:

    ```
    make stop-app
    ```

*   **Parar o banco de dados**:

    ```
    make stop-db
    ```

*   **Parar todos os serviços**:

    ```
    make stop
    ```

*   **Limpar containers e volumes Docker**:

    ```
    make clean
    ```

*   **Exibir ajuda**:

    ```
    make help
    ```

## Interagindo com a API

Após iniciar a aplicação, você pode interagir com a API através dos endpoints disponíveis. A aplicação estará acessível em:

```
http://localhost:8080
```

### Endpoints Disponíveis

#### Clientes (`/customer`)

*   `GET /customer`: Lista todos os clientes.

*   `GET /customer/{id}`: Obtém um cliente específico.

*   `POST /customer`: Cria um novo cliente.

*   `PUT /customer/{id}`: Atualiza um cliente existente.

*   `DELETE /customer/{id}`: Remove um cliente.

#### Atores (`/actor`)

*   `GET /actor`: Lista todos os atores.

*   `GET /actor/{id}`: Obtém um ator específico.

*   `POST /actor`: Cria um novo ator.

*   `PUT /actor/{id}`: Atualiza um ator existente.

*   `DELETE /actor/{id}`: Remove um ator.

#### Filmes (`/movie`)

*   `GET /movie`: Lista todos os filmes.

*   `GET /movie/{id}`: Obtém um filme específico.

*   `POST /movie`: Cria um novo filme.

*   `PUT /movie/{id}`: Atualiza um filme existente.

*   `DELETE /movie/{id}`: Remove um filme.

#### Estoque (`/stock`)

*   `GET /stock`: Lista o estoque de filmes.

*   `GET /stock/{id}`: Obtém informações de estoque específicas.

*   `POST /stock`: Adiciona um novo registro de estoque.

*   `PUT /stock/{id}`: Atualiza um registro de estoque.

*   `DELETE /stock/{id}`: Remove um registro de estoque.

#### Locações (`/rental`)

*   `GET /rental`: Lista todas as locações.

*   `GET /rental/{id}`: Obtém uma locação específica.

*   `POST /rental`: Registra uma nova locação.

*   `PUT /rental/{id}`: Atualiza uma locação existente.

*   `DELETE /rental/{id}`: Remove uma locação.

### Exemplos de Requisições

#### Criar um novo cliente

**Endpoint**: `POST /customer`

**Payload**:

```
{
  "name": "Maria Souza",
  "cpf": "12345678901",
  "email": "maria.souza@example.com",
  "phone": "21987654321",
  "address": "Av. Brasil, 500",
  "birthDate": "1985-06-15"
}
```

**cURL**:

```
curl -X POST http://localhost:8080/customer \
  -H "Content-Type: application/json" \
  -d '{
        "name": "Maria Souza",
        "cpf": "12345678901",
        "email": "maria.souza@example.com",
        "phone": "21987654321",
        "address": "Av. Brasil, 500",
        "birthDate": "1985-06-15"
      }'
```

#### Listar todos os clientes

**Endpoint**: `GET /customer`

**cURL**:

```
curl http://localhost:8080/customer
```

#### Criar um novo ator

**Endpoint**: `POST /actor`

**Payload**:

```
{
  "name": "Leonardo DiCaprio",
  "nationality": "Americano"
}
```

**cURL**:

```
curl -X POST http://localhost:8080/actor \
  -H "Content-Type: application/json" \
  -d '{
        "name": "Leonardo DiCaprio",
        "nationality": "Americano"
      }'
```

#### Criar um novo filme

**Endpoint**: `POST /movie`

**Payload**:

```
{
  "title": "A Origem",
  "genre": "Sci-Fi",
  "synopsis": "Um ladrão que invade os sonhos das pessoas para roubar segredos corporativos.",
  "duration": 148,
  "actorIds": ["<UUID do Leonardo DiCaprio>"]
}
```

Substitua `<UUID do Leonardo DiCaprio>` pelo ID retornado ao criar o ator.

#### Registrar uma nova locação

**Endpoint**: `POST /rental`

**Payload**:

```
{
  "customerId": "<UUID do Cliente>",
  "stockId": "<UUID do Estoque>",
  "rentalDate": "2023-10-01"
}
```

**Nota**: Certifique-se de criar o estoque do filme antes de registrar a locação.

#### Devolver um filme (atualizar locação)

**Endpoint**: `PUT /rental/{id}`

**Payload**:

```
{
  "customerId": "<UUID do Cliente>",
  "stockId": "<UUID do Estoque>",
  "rentalDate": "2023-10-01",
  "returnDate": "2023-10-05"
}
```

### Observações

*   **UUIDs**: IDs são gerados automaticamente. Utilize os IDs retornados nas respostas das requisições.

*   **Formato de Data**: As datas devem estar no formato `YYYY-MM-DD`.

## Parando a Aplicação

Para parar a aplicação e o banco de dados:

```
make stop
```

Para parar individualmente:

*   Apenas a aplicação:

    ```
    make stop-app
    ```

*   Apenas o banco de dados:

    ```
    make stop-db
    ```

## Limpeza de Artefatos

Para remover containers, volumes e arquivos de log:

```
make clean
```

**Atenção**: Isso removerá todos os dados persistidos.

## Observações

*   **Logs da Aplicação**:

    ```
    docker compose logs -f app
    ```

*   **Logs do Banco de Dados**:

    ```
    docker compose logs -f db
    ```

*   **Acesso ao Banco de Dados**:

    ```
    psql -h localhost -p 5432 -U myuser -d dvdrental
    ```

*   **Recompilação**:

    Após alterações no código:

    ```
    make build-app
    make start-app
    ```

*   **Perfis do Spring Boot**:

    *   **Desenvolvimento**:

        *   Perfil: `dev`

        *   Executar localmente:

            ```
            mvn spring-boot:run
            ```

    *   **Produção**:

        *   Perfil: `prod`

        *   Configurações no `docker-compose.yml`.

*   **Variáveis de Ambiente**:

    Ajuste as credenciais conforme necessário.

*   **Makefile**:

    Certifique-se de ter o **Make** instalado.

