# Dockerfile

# Etapa 1: Build da aplicação usando a imagem oficial do Maven com OpenJDK 17
FROM maven:3.8.6-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Configuração da imagem final para execução
FROM ubuntu:20.04

ENV DEBIAN_FRONTEND=noninteractive

# Atualizar e instalar dependências
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk nginx curl supervisor && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Definir variáveis de ambiente
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# Criar um usuário não-root para execução
RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup

# Diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o JAR da etapa de build
COPY --from=builder /app/target/*.jar app.jar

# Copiar a configuração do Nginx
COPY nginx/nginx.conf /etc/nginx/nginx.conf

# Copiar o arquivo de configuração do Supervisord
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# Criar diretórios de log
RUN mkdir -p /var/log/supervisor /var/log/java /var/log/nginx

# Ajustar permissões dos diretórios de log
RUN chown -R root:root /var/log/supervisor && \
    chown -R appuser:appgroup /var/log/java && \
    chown -R appuser:appgroup /var/log/nginx

# Expor a porta 80 (Nginx)
EXPOSE 80

# Adicionar HEALTHCHECK
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s \
  CMD curl -f http://localhost/health || exit 1

# Definir o Supervisord como entrypoint
ENTRYPOINT ["/usr/bin/supervisord"]
