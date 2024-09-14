# Projeto Web Application com Spring Boot e PostgreSQL

Este projeto é uma aplicação web desenvolvida em Java utilizando o framework Spring Boot. A aplicação faz uso do Spring Data JPA para interagir com um banco de dados PostgreSQL e está containerizada utilizando Docker e Docker Compose para facilitar a implantação e execução.

## Índice

*   [Pré-requisitos](#pr%C3%A9-requisitos)

*   [Tecnologias Utilizadas](#tecnologias-utilizadas)

*   [Instalação e Configuração](#instala%C3%A7%C3%A3o-e-configura%C3%A7%C3%A3o)

    *   [Clonar o Repositório](#clonar-o-reposit%C3%B3rio)

    *   [Configuração de Variáveis de Ambiente](#configura%C3%A7%C3%A3o-de-vari%C3%A1veis-de-ambiente)

*   [Construção e Execução](#constru%C3%A7%C3%A3o-e-execu%C3%A7%C3%A3o)

    *   [Comandos Principais](#comandos-principais)

*   [Acessando a Aplicação](#acessando-a-aplica%C3%A7%C3%A3o)

*   [Parando a Aplicação](#parando-a-aplica%C3%A7%C3%A3o)

*   [Limpeza de Artefatos](#limpeza-de-artefatos)

*   [Observações](#observa%C3%A7%C3%B5es)

*   [Licença](#licen%C3%A7a)

## Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em seu ambiente de desenvolvimento:

*   **Java 17** ou superior

*   **Maven 3.6+** (se não estiver usando o Maven Wrapper)

*   **Docker**

*   **Docker Compose**

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
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio

```

### Configuração de Variáveis de Ambiente

A aplicação utiliza variáveis de ambiente para configurar a conexão com o banco de dados. Você pode definir essas variáveis em um arquivo `.env` na raiz do projeto ou diretamente no ambiente do sistema.

**Criando um arquivo `.env`**:

Crie um arquivo chamado `.env` na raiz do projeto com o seguinte conteúdo:

```
# Configurações do Banco de Dados
POSTGRES_USER=myuser
POSTGRES_PASSWORD=mypassword
POSTGRES_DB=mydatabase

# Configurações da Aplicação
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/mydatabase
SPRING_DATASOURCE_USERNAME=myuser
SPRING_DATASOURCE_PASSWORD=mypassword

```

**Observação**: Certifique-se de que as credenciais definidas no `.env` correspondem às utilizadas no arquivo `docker-compose.yml`.

## Construção e Execução

O projeto inclui um `Makefile` que simplifica a execução de comandos comuns. Abaixo estão os comandos principais para construir e executar a aplicação.

### Comandos Principais

*   **Iniciar apenas o banco de dados**:

    ```
    make start-db

    ```

    Este comando inicia o serviço do PostgreSQL usando Docker Compose.

*   **Construir a imagem da aplicação**:

    ```
    make build-app

    ```

    Compila a aplicação e constrói a imagem Docker correspondente.

*   **Iniciar apenas a aplicação**:

    ```
    make start-app

    ```

    Inicia o serviço da aplicação Spring Boot. Este comando também irá iniciar o banco de dados se ele ainda não estiver em execução.

*   **Iniciar o banco de dados e a aplicação**:

    ```
    make start

    ```

    Inicia ambos os serviços: banco de dados e aplicação.

*   **Parar a aplicação**:

    ```
    make stop-app

    ```

    Para o serviço da aplicação.

*   **Parar o banco de dados**:

    ```
    make stop-db

    ```

    Para o serviço do banco de dados PostgreSQL.

*   **Parar ambos os serviços**:

    ```
    make stop

    ```

*   **Limpar containers e volumes Docker**:

    ```
    make clean

    ```

    Este comando para todos os serviços e remove os containers e volumes associados.

## Acessando a Aplicação

Após iniciar a aplicação, você pode acessá-la através do seguinte endereço:

```
http://localhost:8080

```

Certifique-se de que a porta `8080` não esteja sendo utilizada por outro serviço em sua máquina.

## Parando a Aplicação

Para parar a aplicação e o banco de dados, você pode utilizar o comando:

```
make stop

```

Isso irá parar ambos os serviços. Se você quiser parar apenas um dos serviços, use `make stop-app` ou `make stop-db`.

## Limpeza de Artefatos

Para limpar os containers Docker, volumes e arquivos de log, utilize:

```
make clean

```

**Atenção**: Este comando removerá os dados persistidos no volume do PostgreSQL. Use com cautela se não quiser perder os dados armazenados.

## Observações

*   **Logs da Aplicação**: Você pode visualizar os logs da aplicação usando:

    ```
    docker compose logs -f app

    ```

*   **Logs do Banco de Dados**: Para visualizar os logs do PostgreSQL:

    ```
    docker compose logs -f db

    ```

*   **Acesso ao Banco de Dados**: Você pode acessar o banco de dados usando uma ferramenta como o `psql` ou um cliente gráfico como o DBeaver. As credenciais são as definidas no arquivo `.env`.

    **Exemplo usando `psql`**:

    ```
    psql -h localhost -p 5432 -U myuser -d mydatabase

    ```

*   **Recompilação da Aplicação**: Sempre que fizer alterações no código-fonte, reconstrua a imagem da aplicação:

    ```
    make build-app

    ```

    Em seguida, reinicie a aplicação:

    ```
    make start-app

    ```

*   **Variáveis de Ambiente Sensíveis**: Nunca comite o arquivo `.env` com credenciais reais em um repositório público. Adicione o `.env` ao seu `.gitignore`:

    ```
    .env

    ```

## Licença

Este projeto está licenciado sob os termos da licença MIT. Consulte o arquivo LICENSE para obter mais informações.
