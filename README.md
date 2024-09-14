# Projeto Web Application com Spring Boot e PostgreSQL

Este projeto é uma aplicação web desenvolvida em Java utilizando o framework Spring Boot. A aplicação faz uso do Spring Data JPA para interagir com um banco de dados PostgreSQL e está containerizada utilizando Docker e Docker Compose para facilitar a implantação e execução. O projeto inclui um **Makefile** para simplificar a execução de comandos comuns.

## Índice

*   [Pré-requisitos](#pr%C3%A9-requisitos)

*   [Tecnologias Utilizadas](#tecnologias-utilizadas)

*   [Instalação e Configuração](#instala%C3%A7%C3%A3o-e-configura%C3%A7%C3%A3o)

    *   [Clonar o Repositório](#clonar-o-reposit%C3%B3rio)

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

Aqui estão os comandos disponíveis no Makefile:

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

    Inicia o serviço da aplicação Spring Boot. Este comando também irá construir a imagem da aplicação se ainda não existir.

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

*   **Ver ajuda dos comandos**:

    ```
    make help
    ```

    Exibe a lista de comandos disponíveis.

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

*   **Acesso ao Banco de Dados**: Você pode acessar o banco de dados usando uma ferramenta como o `psql` ou um cliente gráfico como o DBeaver. As credenciais estão definidas no arquivo `docker-compose.yml`.

    **Exemplo usando `psql`**:

    ```
    psql -h localhost -p 5432 -U myuser -d dvdrental
    ```

*   **Recompilação da Aplicação**: Sempre que fizer alterações no código-fonte, reconstrua a imagem da aplicação:

    ```
    make build-app
    ```

    Em seguida, reinicie a aplicação:

    ```
    make start-app
    ```

*   **Perfis do Spring Boot**: A aplicação utiliza perfis para separar as configurações de desenvolvimento e produção.

    *   **Desenvolvimento**:

        *   Perfil ativo: `dev` (definido no `pom.xml` e no `application.properties`).

        *   Configurações em `application-dev.properties`.

        *   O banco de dados local é acessado via `localhost`.

        *   **Execução em ambiente de desenvolvimento**: Você pode executar a aplicação localmente, sem Docker, usando:

            ```
            mvn spring-boot:run
            ```

        *   Certifique-se de ter uma instância do PostgreSQL rodando localmente ou ajuste as configurações conforme necessário.

    *   **Produção**:

        *   Perfil ativo: `prod` (definido via variável de ambiente `SPRING_PROFILES_ACTIVE` no `docker-compose.yml`).

        *   Configurações em `application-prod.properties`.

        *   As variáveis de ambiente para conexão ao banco de dados são definidas no `docker-compose.yml`.

*   **Variáveis de Ambiente Sensíveis**: As credenciais e informações sensíveis estão definidas no `docker-compose.yml`. Certifique-se de não expor informações confidenciais em repositórios públicos. Ajuste as credenciais conforme necessário para seu ambiente.

*   **Makefile**: O Makefile facilita a execução de comandos comuns. Certifique-se de ter o **Make** instalado em seu sistema. No Windows, você pode utilizar o WSL ou instalar o Make via Git Bash ou outras ferramentas.

## Licença

Este projeto está licenciado sob os termos da licença MIT. Consulte o arquivo LICENSE para obter mais informações.
