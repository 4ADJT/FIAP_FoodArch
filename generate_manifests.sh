#!/bin/bash

# Nome da imagem Docker
IMAGE_NAME="kompose-generator"

# Caminho para o diretório onde os manifestos serão gerados
OUTPUT_DIR="k8s-manifests"

echo "Limpando o diretório de saída..."
rm -rf $OUTPUT_DIR

echo "Construindo a imagem Docker..."
docker build -f kompose.Dockerfile -t $IMAGE_NAME .

echo "Gerando os manifestos Kubernetes..."
docker run --rm -v $(pwd):/app $IMAGE_NAME

echo "Manifestos gerados em $OUTPUT_DIR"
