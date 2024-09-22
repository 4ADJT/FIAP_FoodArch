#!/bin/bash

# Verificar se os argumentos foram fornecidos
if [ $# -ne 2 ]; then
    echo "Uso: $0 <image_tag> <docker_password>"
    exit 1
fi

# Variáveis
DOCKER_USER="rbrocchi"
IMAGE_NAME="foodarch"
IMAGE_TAG=$1
DOCKER_PASSWORD=$2

# Construir a imagem Docker
echo "Construindo a imagem Docker..."
docker build -t $DOCKER_USER/$IMAGE_NAME:$IMAGE_TAG .

# Verificar se o build foi bem-sucedido
if [ $? -ne 0 ]; then
    echo "Erro ao construir a imagem Docker."
    exit 1
fi

# Fazer login no Docker Hub
echo "Fazendo login no Docker Hub..."
echo $DOCKER_PASSWORD | docker login -u $DOCKER_USER --password-stdin

# Verificar se o login foi bem-sucedido
if [ $? -ne 0 ]; then
    echo "Erro ao fazer login no Docker Hub."
    exit 1
fi

# Enviar a imagem para o Docker Hub
echo "Enviando a imagem para o Docker Hub..."
docker push $DOCKER_USER/$IMAGE_NAME:$IMAGE_TAG

# Verificar se o push foi bem-sucedido
if [ $? -ne 0 ]; then
    echo "Erro ao enviar a imagem para o Docker Hub."
    exit 1
fi

echo "Imagem enviada com sucesso para $DOCKER_USER/$IMAGE_NAME:$IMAGE_TAG"
