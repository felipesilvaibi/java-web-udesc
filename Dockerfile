# Etapa 1: Construção
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo pom.xml e os arquivos de configuração
COPY pom.xml mvnw ./

# Copiar a pasta .mvn se você estiver usando o Maven Wrapper
COPY .mvn .mvn

# Baixar as dependências sem compilar o código
RUN mvn dependency:go-offline

# Copiar o código-fonte do projeto
COPY src ./src

# Compilar o projeto
RUN mvn clean package -DskipTests

# Etapa 2: Execução
FROM eclipse-temurin:17-jre-alpine

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR da etapa de construção
COPY --from=build /app/target/*.jar app.jar

# Expor a porta que a aplicação utiliza
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
