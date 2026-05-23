CREATE DATABASE incluime;
USE incluime;

-- Tabela usuário
CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  sobrenome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  senha VARCHAR(100) NOT NULL,
  tipo_usuario INT NOT NULL,
  nome_escola VARCHAR(100)
);

-- Tabela escola
CREATE TABLE escola (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome_escola VARCHAR(100) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  endereco_id INT,
  usuario_id INT,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Tabela mensagens
CREATE TABLE mensagens_contate (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  tipo_contato VARCHAR(50),
  mensagem TEXT,
  data_envio DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabela avaliação
CREATE TABLE avaliacao (
  id INT AUTO_INCREMENT PRIMARY KEY,
  descricao TEXT,
  nota INT,
  dtComentario DATETIME,
  usuario_id INT,
  escola_id INT,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  FOREIGN KEY (escola_id) REFERENCES escola(id)
);

ALTER TABLE avaliacao
MODIFY dtComentario DATETIME DEFAULT CURRENT_TIMESTAMP;


-- Tabela meta
CREATE TABLE meta (
  id INT AUTO_INCREMENT PRIMARY KEY,
  valor DOUBLE,
  usuario_id INT,
  escola_id INT,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  FOREIGN KEY (escola_id) REFERENCES escola(id)
);


-- Base censo escolar
CREATE TABLE base_dados_censo_escolar (
  id INT AUTO_INCREMENT PRIMARY KEY,
  ano INT,
  sigla_uf CHAR(2),
  id_municipio CHAR(7),
  id_municipio_nome VARCHAR(45),
  id_escola VARCHAR(45),
  rede VARCHAR(45),
  tipo_categoria_escola_privada VARCHAR(45),
  tipo_localizacao VARCHAR(45),
  banheiro_pne INT,
  dependencia_pne INT,
  acessibilidade_corrimao INT,
  acessibilidade_elevador INT,
  acessibilidade_pisos_tateis INT,
  acessibilidade_vao_livre INT,
  acessibilidade_rampas INT,
  acessibilidade_sinais_sonoros INT,
  acessibilidade_sinal_tatil INT,
  acessibilidade_sinal_visual INT,
  acessibilidade_inexistente INT,
  quantidade_sala_utilizade_acessivel INT,
  material_pedagogico_surdo INT,
  quantidade_matricula_educacao_basica INT,
  quantidade_matricula_especial INT,
  quantidade_docente_educacao_basica INT,
  quantidade_turma_especial INT,
  quantidade_turma_especial_comum INT,
  quantidade_turma_especial_exclusiva INT
);


CREATE TABLE logss (
  id INT PRIMARY KEY AUTO_INCREMENT,
  acao VARCHAR(250),
  tipo VARCHAR(50),
  dtHora DATETIME DEFAULT CURRENT_TIMESTAMP
);
