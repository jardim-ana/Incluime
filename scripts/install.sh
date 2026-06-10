#!/bin/bash

set -e

echo "=== Atualizando sistema ==="
sudo apt update

echo "=== Instalando Git ==="
if ! command -v git >/dev/null 2>&1; then
    sudo apt install -y git
fi

echo "=== Instalando Docker ==="
if ! command -v docker >/dev/null 2>&1; then
    sudo apt install -y docker.io
    sudo systemctl enable docker
    sudo systemctl start docker
fi

echo "=== Instalando Docker Compose ==="
if ! command -v docker-compose >/dev/null 2>&1; then
    sudo apt install -y docker-compose
fi

echo "=== Verificando Docker ==="
docker --version
docker-compose --version

echo "=== Baixando imagens ==="
docker-compose pull

echo "=== Subindo containers ==="
docker-compose down
docker-compose up -d

echo "=== Status dos containers ==="
docker ps -a

echo "=== Instalação concluída ==="
echo "Acesse a aplicação em: http://IP_DA_MAQUINA:3000"