# Nome da imagem Docker
$IMAGE_NAME = "kompose-generator"

# Caminho para o diretório onde os manifestos serão gerados
$OutputDir = "k8s-manifests"

Write-Host "Limpando o diretório de saída..."
if (Test-Path $OutputDir) {
    Remove-Item -Recurse -Force $OutputDir
}

Write-Host "Construindo a imagem Docker..."
docker build -f kompose.Dockerfile -t $IMAGE_NAME .

Write-Host "Gerando os manifestos Kubernetes..."
docker run --rm -v ${PWD}:/app $IMAGE_NAME

Write-Host "Manifestos gerados em $OutputDir"
