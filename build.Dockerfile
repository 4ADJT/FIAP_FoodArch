# Build

# Etapa 1: Build da aplicação usando Maven e OpenJDK 17
FROM ubuntu:20.04 AS builder

# Evitar interações durante a instalação de pacotes
ENV DEBIAN_FRONTEND=noninteractive

# Atualizar e instalar dependências
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y openjdk-17-jdk maven curl nginx supervisor && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Definir diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml e baixar dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o código fonte da aplicação
COPY src ./src

# Compilar e empacotar a aplicação, pulando os testes
RUN mvn clean package -DskipTests

# Etapa 2: Configuração da imagem final para execução
FROM ubuntu:20.04

# Evitar interações durante a instalação de pacotes
ENV DEBIAN_FRONTEND=noninteractive

# Atualizar e instalar dependências
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk nginx curl supervisor && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Definir variáveis de ambiente
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# Diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o JAR da etapa de build
COPY --from=builder /app/target/*.jar app.jar

# Copiar a configuração do Nginx
COPY nginx/nginx.conf /etc/nginx/nginx.conf

# Copiar o arquivo de configuração do Supervisord
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# Criar diretórios de log para a aplicação Java
RUN mkdir -p /var/log/java && \
    chown -R root:root /var/log/java

# Copiar o supervisord.conf para definir o proprietário dos logs
# (se necessário, ajustar permissões adicionais)

# Criar um usuário não-root para execução
RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup

# Alterar propriedade dos diretórios de log para appuser
RUN chown -R appuser:appgroup /var/log/nginx && \
    chown -R appuser:appgroup /var/log/java

# Alterar para o usuário não-root
USER appuser

# Expor a porta 80 (Nginx)
EXPOSE 80

# Adicionar HEALTHCHECK
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s \
  CMD curl -f http://localhost/health || exit 1

# Definir o Supervisord como entrypoint
ENTRYPOINT ["/usr/bin/supervisord"]
