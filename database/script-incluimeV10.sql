-- Active: 1776356926070@@127.0.0.1@3306@incluime
CREATE DATABASE incluime;
USE incluime;

-- Tabela endereço
CREATE TABLE endereco (
id INT AUTO_INCREMENT PRIMARY KEY, 
logradouro VARCHAR(150), 
numero VARCHAR(20), 
CEP VARCHAR(10)
);

-- Tabela escola
CREATE TABLE escola (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome_escola VARCHAR(100) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  endereco_id INT, 
  FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

-- Tabela usuário
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

-- Base censo escolar
CREATE TABLE base_dados_censo_escolar (
  id INT AUTO_INCREMENT PRIMARY KEY,
  ano INT,
  sigla_uf CHAR(2),
  id_municipio CHAR(7),
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

-- Base censo escolar: campos de acessibilidade
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
  FOREIGN KEY (censo_escolar_id) REFERENCES base_dados_censo_escolar(id)
  ); 
  
  -- Base censo escolar: campos de quantidades
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

-- Tabela avaliação
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

-- Tabela meta
CREATE TABLE meta (
  id INT AUTO_INCREMENT PRIMARY KEY,
  valor DOUBLE,
  valor_base DOUBLE, 
  usuario_id INT,
  escola_id INT,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  FOREIGN KEY (escola_id) REFERENCES escola(id)
);

-- Tabela mensagens
CREATE TABLE mensagens_contate_nos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  tipo_contato VARCHAR(50),
  mensagem TEXT,
  data_envio DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabela dos logs
CREATE TABLE logs_sistema (
  id INT AUTO_INCREMENT PRIMARY KEY,
  acao VARCHAR(250),
  tipo VARCHAR(50),
  dtHora DATETIME DEFAULT CURRENT_TIMESTAMP
);
select * from usuario;