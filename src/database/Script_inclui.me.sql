CREATE DATABASE Incluime;
USE Incluime;


CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    nivel_acesso TINYINT NOT NULL,
    CONSTRAINT chk_nivel_acesso 
    CHECK (nivel_acesso IN (0,1))
);

CREATE TABLE deficiencia (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE endereco (
    id INT PRIMARY KEY AUTO_INCREMENT,
    logradouro VARCHAR(100) NOT NULL,
    regiao VARCHAR(50) NOT NULL,
    numero VARCHAR(15) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL
);

CREATE TABLE estudante (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE,
    observacoes TEXT,
    fk_usuario INT,
    fk_deficiencia INT,
    fk_endereco INT,
    FOREIGN KEY (fk_usuario) REFERENCES usuario(id),
    FOREIGN KEY (fk_deficiencia) REFERENCES deficiencia(id),
    FOREIGN KEY (fk_endereco) REFERENCES endereco(id)
);


CREATE TABLE escola (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome_escola VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    fk_endereco INT,
    FOREIGN KEY (fk_endereco) REFERENCES endereco(id)
);

CREATE TABLE matricula (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fk_estudante INT,
    fk_escola INT,
    data_inicio DATE,
    status VARCHAR(50),
    FOREIGN KEY (fk_estudante) REFERENCES estudante(id),
    FOREIGN KEY (fk_escola) REFERENCES escola(id)
);


CREATE TABLE avaliacao_escolar (
    id INT PRIMARY KEY AUTO_INCREMENT,
    comentario TEXT,
    nota INT,
    data_avaliacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fk_usuario INT,
    fk_deficiencia INT,
    CONSTRAINT chk_nota 
    CHECK (nota >= 1 AND nota <= 5),
    FOREIGN KEY (fk_usuario) REFERENCES usuario(id),
    FOREIGN KEY (fk_deficiencia) REFERENCES deficiencia(id)
);

CREATE TABLE mensagens_contato (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    tipo_contato VARCHAR(50),
    mensagem TEXT,
    data_envio DATETIME DEFAULT CURRENT_TIMESTAMP
);