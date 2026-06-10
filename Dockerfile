# Imagem base
FROM node:20

# Instala Python e pip
RUN apt-get update && \
    apt-get install -y python3 python3-pip && \
    rm -rf /var/lib/apt/lists/*

# Instala bibliotecas Python
RUN python3 -m pip install flask flask-cors --break-system-packages

# Diretório dentro do container
WORKDIR /app

# Copia arquivos de dependência
COPY package*.json ./

# Instala dependências Node
RUN npm install

# Copia o restante da aplicação
COPY . .

# Porta da aplicação
EXPOSE 3000

# Comando inicial
CMD ["npm", "start"]
