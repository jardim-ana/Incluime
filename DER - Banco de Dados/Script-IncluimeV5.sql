CREATE DATABASE Incluime;
USE Incluime;


CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    sobrenome VARCHAR(45) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    tipo_usuario TINYINT NOT NULL,
    CHECK (tipo_usuario IN (0,1))
);


CREATE TABLE deficiencia (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(45) NOT NULL,
    descricao TEXT,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) 
);


CREATE TABLE endereco (
    id INT PRIMARY KEY AUTO_INCREMENT,
    logradouro VARCHAR(100),
    numero VARCHAR(10),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    cep CHAR(8)
);

CREATE TABLE escola (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome_escola VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    endereco_id INT,
    usuario_id INT,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id),
	FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE matricula (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data_inicio DATE,
    statuss VARCHAR(50),
    usuario_id INT NOT NULL,
    escola_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (escola_id) REFERENCES escola(id)
);

CREATE TABLE avaliacao (
    id INT AUTO_INCREMENT,
    comentario TEXT,
    nota INT,
    data_avaliacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	usuario_id INT,
    escola_id INT,
    PRIMARY KEY (id, usuario_id, escola_id),
    CHECK (nota >=1 AND nota <=5),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (escola_id) REFERENCES escola(id)
);

CREATE TABLE laudo (
    id INT AUTO_INCREMENT,
    arquivo BLOB,
    data_envio DATETIME DEFAULT CURRENT_TIMESTAMP,
    usuario_id INT,
    PRIMARY KEY (id, usuario_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE base_dados (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ano YEAR,
    sigla_uf CHAR(2),
    id_municipio CHAR(7),
    tipo_classe VARCHAR(45),
    tipo_deficiencia VARCHAR(45),
    quantidade_matricula FLOAT
);

CREATE TABLE contate_nos (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    email VARCHAR(100) NOT NULL,
    tipo_contato VARCHAR(50),
    mensagem TEXT,
    data_envio DATETIME DEFAULT CURRENT_TIMESTAMP
);








