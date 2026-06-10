CREATE DATABASE incluime;
USE incluime;

CREATE TABLE endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    logradouro VARCHAR(150),
    numero VARCHAR(20),
    cep VARCHAR(10)
);

CREATE TABLE escola (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_inep VARCHAR(20) UNIQUE,
    nome_escola VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    endereco_id INT,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  sobrenome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  senha VARCHAR(100) NOT NULL,
  tipo_usuario INT NOT NULL, 
  codigo_inep VARCHAR(20) UNIQUE, 
  id_escola INT,
  notificacao_email TINYINT DEFAULT 0,
  FOREIGN KEY (id_escola) REFERENCES escola(id)
);

CREATE TABLE base_dados_censo_escolar (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ano INT,
    sigla_uf CHAR(2),
    id_municipio INT,
    id_municipio_nome VARCHAR(45),
    escola_id INT,
    rede VARCHAR(45),
    tipo_categoria_escola_privada VARCHAR(45),
    tipo_localizacao VARCHAR(45),
    banheiro_pne INT,
    dependencia_pne INT,
    material_pedagogico_surdo INT,
    FOREIGN KEY (escola_id) REFERENCES escola(id)
);

CREATE TABLE base_dados_acessibilidade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    censo_escolar_id INT,
    acessibilidade_corrimao INT,
    acessibilidade_elevador INT,
    acessibilidade_pisos_tateis INT,
    acessibilidade_vao_livre INT,
    acessibilidade_rampas INT,
    acessibilidade_sinais_sonoros INT,
    acessibilidade_sinal_tatil INT,
    acessibilidade_sinal_visual INT,
    acessibilidade_inexistente INT,
    FOREIGN KEY (censo_escolar_id)
        REFERENCES base_dados_censo_escolar(id)
);

CREATE TABLE base_dados_quantidades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ano INT,
    escola_id INT,
    quantidade_sala_utilizada_acessivel INT,
    quantidade_matricula_educacao_basica INT,
    quantidade_matricula_especial INT,
    quantidade_docente_educacao_basica INT,
    quantidade_turma_especial INT,
    quantidade_turma_especial_comum INT,
    quantidade_turma_especial_exclusiva INT,
    FOREIGN KEY (escola_id) REFERENCES escola(id)
);

CREATE TABLE tipo_deficiencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO tipo_deficiencia (nome)
VALUES
('Fisica'),
('Visual'),
('Auditiva'),
('Intelectual');

CREATE TABLE escola_deficiencia (
    escola_id INT,
    deficiencia_id INT,

    PRIMARY KEY (escola_id, deficiencia_id),

    FOREIGN KEY (escola_id)
        REFERENCES escola(id),

    FOREIGN KEY (deficiencia_id)
        REFERENCES tipo_deficiencia(id)
);

CREATE TABLE avaliacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT,
    nota INT,
    dtComentario DATETIME DEFAULT CURRENT_TIMESTAMP,
    receber_notificacao TINYINT,
    usuario_id INT,
    escola_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (escola_id) REFERENCES escola(id)
);

CREATE TABLE meta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    escola_id INT UNIQUE,
    meta_matricula DOUBLE DEFAULT 10.0,
    meta_acessibilidade DOUBLE DEFAULT 70.0,

    FOREIGN KEY (escola_id)
        REFERENCES escola(id)
);

CREATE TABLE mensagens_contate_nos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    tipo_contato VARCHAR(50),
    mensagem TEXT,
    data_envio DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE logs_sistema (
    id INT AUTO_INCREMENT PRIMARY KEY,
    acao VARCHAR(250),
    tipo VARCHAR(50),
    dtHora DATETIME DEFAULT CURRENT_TIMESTAMP
);