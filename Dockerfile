# Etapa 1: Build da aplicação usando uma imagem com Maven e OpenJDK 17
FROM maven:3.8.1-openjdk-17-slim AS builder

# Diretório de trabalho para o build
WORKDIR /app

# Copiar o arquivo pom.xml e baixar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o código fonte da aplicação
COPY src ./src

# Compilar e empacotar a aplicação
RUN mvn clean package

# Etapa 2: Configuração da imagem final para execução usando uma imagem leve do OpenJDK
FROM openjdk:17-slim

# Diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o JAR da etapa de build
COPY --from=builder /app/target/*.jar app.jar

# Definir variáveis de ambiente
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-Xms512m -Xmx1024m"
ENV SERVER_PORT=8080

# Expor a porta padrão da aplicação
EXPOSE 8080

# Comando para rodar a aplicação Java, garantindo que escute em todas as interfaces
CMD ["java", "-jar", "/app/app.jar", "--server.address=0.0.0.0"]
