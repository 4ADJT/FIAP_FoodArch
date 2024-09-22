FROM alpine:latest

# Instalar as dependências necessárias
RUN apk add --no-cache curl

# Baixar o binário do Kompose e torná-lo executável
RUN curl -L https://github.com/kubernetes/kompose/releases/download/v1.26.1/kompose-linux-amd64 -o /usr/local/bin/kompose \
    && chmod +x /usr/local/bin/kompose

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo docker-compose-k8s.yml para o container
COPY docker-compose-k8s.yml /app/

# Comando para executar o Kompose usando o arquivo especificado
CMD ["kompose", "convert", "-f", "docker-compose-k8s.yml", "-o", "k8s-manifests/"]
