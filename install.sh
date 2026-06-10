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
fi

sudo systemctl enable docker
sudo systemctl start docker

echo "=== Instalando Docker Compose ==="
if ! docker compose version >/dev/null 2>&1; then
    sudo apt install -y docker-compose-v2
fi

echo "=== Verificando versões ==="
docker --version
docker compose version

echo "=== Verificando docker-compose.yml ==="
if [ ! -f "docker-compose.yml" ]; then
    echo "ERRO: docker-compose.yml não encontrado na pasta atual."
    echo "Execute este script na raiz do projeto."
    exit 1
fi

echo "=== Baixando imagens ==="
docker compose pull

echo "=== Subindo containers ==="
docker compose down
docker compose up -d

echo "=== Status dos containers ==="
docker ps -a

echo "=== Instalação concluída ==="
echo "Acesse a aplicação em: http://IP_DA_MAQUINA:3000"#!/bin/bash

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
fi

sudo systemctl enable docker
sudo systemctl start docker

echo "=== Instalando Docker Compose ==="
if ! docker compose version >/dev/null 2>&1; then
    sudo apt install -y docker-compose-v2
fi

echo "=== Verificando versões ==="
docker --version
docker compose version

echo "=== Verificando docker-compose.yml ==="
if [ ! -f "docker-compose.yml" ]; then
    echo "ERRO: docker-compose.yml não encontrado na pasta atual."
    echo "Execute este script na raiz do projeto."
    exit 1
fi

echo "=== Baixando imagens ==="
docker compose pull

echo "=== Subindo containers ==="
docker compose down
docker compose up -d

echo "=== Status dos containers ==="
docker ps -a

echo "=== Instalação concluída ==="
echo "Acesse a aplicação em: http://IP_DA_MAQUINA:3000"
