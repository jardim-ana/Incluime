CREATE DATABASE incluime;
USE incluime;

-- Tabela usuário
CREATE TABLE usuario (
  usuario_id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  sobrenome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  senha VARCHAR(100) NOT NULL,
  nome_escola VARCHAR(100),
  tipo_usuario INT NOT NULL
);

-- Tabela escola
CREATE TABLE escola (
  escola_id INT AUTO_INCREMENT PRIMARY KEY,
  nome_escola VARCHAR(100) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  usuario_id INT,
  FOREIGN KEY (usuario_fk) REFERENCES usuario(id), 
  FOREIGN KEY (base_dados_quantidades_fk) REFERENCES base_dados_quantidades(quantidade_id), 
  FOREIGN KEY (base_dados_acessibilidade_fk) REFERENCES base_dados_acessibilidade(acessibilidade_id), 
  FOREIGN KEY (base_dados_censo_escolar_fk) REFERENCES base_dados_censo_escolar(base_id)
);

CREATE TABLE endereco (
endereco_id INT AUTO_INCREMENT PRIMARY KEY, 
rua VARCHAR(45), 
numero INT NOT NULL, 
bairro VARCHAR(45), 
cidade VARCHAR(45), 
estado CHAR(2), 
FOREIGN KEY (escola_fk) REFERENCES escola(escola_id)
)
;

-- Tabela mensagens
CREATE TABLE mensagens_contate_nos (
  mensagem_id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  tipo_contato VARCHAR(50),
  mensagem TEXT,
  data_envio DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabela avaliação
CREATE TABLE avaliacao (
  avaliacao_id INT AUTO_INCREMENT PRIMARY KEY,
  descricao TEXT,
  nota INT,
  dtComentario DATETIME DEFAULT CURRENT_TIMESTAMP,
  usuario_id INT,
  escola_id INT,
  FOREIGN KEY (usuario_fk) REFERENCES usuario(id),
  FOREIGN KEY (escola_fk) REFERENCES escola(id)
);

-- Tabela meta
CREATE TABLE meta (
  meta_id INT AUTO_INCREMENT PRIMARY KEY,
  valor DOUBLE,
  usuario_id INT,
  escola_id INT,
  FOREIGN KEY (usuario_fk) REFERENCES usuario(id),
  FOREIGN KEY (escola_fk) REFERENCES escola(id)
);

CREATE TABLE logs_sistema (
  logs_id INT AUTO_INCREMENT PRIMARY KEY,
  acao VARCHAR(250),
  tipo VARCHAR(50),
  dtHora DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Base censo escolar
CREATE TABLE base_dados_censo_escolar (
  base_id INT AUTO_INCREMENT PRIMARY KEY,
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
  material_pedagogico_surdo INT
);

-- Base censo escolar: campos de acessibilidade
CREATE TABLE base_dados_acessibilidade (
  acessibilidade_id INT AUTO_INCREMENT PRIMARY KEY, 
  acessibilidade_corrimao INT,
  acessibilidade_elevador INT,
  acessibilidade_pisos_tateis INT,
  acessibilidade_vao_livre INT,
  acessibilidade_rampas INT,
  acessibilidade_sinais_sonoros INT,
  acessibilidade_sinal_tatil INT,
  acessibilidade_sinal_visual INT,
  acessibilidade_inexistente INT
  ); 
  
  -- Base censo escolar: campos de quantidades
  CREATE TABLE base_dados_quantidades (
  quantidade_id INT AUTO_INCREMENT PRIMARY KEY,  
  quantidade_sala_utilizada_acessivel INT,
  quantidade_matricula_educacao_basica INT,
  quantidade_matricula_especial INT,
  quantidade_docente_educacao_basica INT,
  quantidade_turma_especial INT,
  quantidade_turma_especial_comum INT,
  quantidade_turma_especial_exclusiva INT
  );

select * from usuario;