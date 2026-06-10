# 📚 inclui.me

## 🚀 Projeto: Conecta+

Tecnologia a serviço da inclusão escolar.

---

# 📌 Sobre o Projeto

O **Conecta+** é uma plataforma desenvolvida pela **inclui.me** com o objetivo de fortalecer a educação inclusiva no Brasil, promovendo a conexão entre famílias, escolas e órgãos públicos.

A solução busca reduzir barreiras enfrentadas por estudantes com deficiência ou necessidades educacionais especiais, fornecendo mecanismos de acompanhamento, comunicação e geração de indicadores para apoio à tomada de decisão.

---

# ⚠️ Problema

Apesar dos avanços promovidos pela legislação brasileira e pelas políticas públicas de inclusão, muitas famílias ainda enfrentam dificuldades como:

* ❌ Falta de acompanhantes especializados;
* ❌ Recusa ou dificultação de matrículas;
* ❌ Estrutura escolar inadequada;
* ❌ Falta de monitoramento do cumprimento da legislação.

---

# 💡 Solução

O **Conecta+** foi criado para centralizar informações e promover maior transparência sobre a realidade da inclusão escolar.

A plataforma permite:

* Registro e acompanhamento de estudantes;
* Avaliação e feedback de instituições de ensino;
* Produção de indicadores e relatórios;
* Apoio à gestão escolar;
* Compartilhamento de informações sobre direitos educacionais.

---

# 🎯 Objetivos

* Mapear situações de exclusão e recusa de matrícula;
* Produzir dados sobre inclusão escolar;
* Apoiar gestores na tomada de decisão;
* Aproximar famílias e instituições de ensino;
* Promover permanência e inclusão efetiva dos estudantes.

---

# 🧩 Funcionalidades

## 👨‍👩‍👧 Área das Famílias

* Cadastro de estudantes;
* Acompanhamento escolar;
* Consulta de direitos garantidos por lei;
* Avaliação de escolas;
* Consulta de informações sobre inclusão.

## 🏫 Área das Escolas

* Gestão de estudantes que necessitam de acompanhamento;
* Relatórios internos;
* Canal de comunicação com responsáveis;
* Compartilhamento de boas práticas de inclusão.

---

# 🛠️ Tecnologias Utilizadas

## Front-end

* HTML5
* CSS3
* JavaScript

## Back-end

* Java
* Node.js
* API Web-Data-Viz

## Banco de Dados

* MySQL

## Infraestrutura

* Docker
* Docker Compose
* Linux (Ubuntu)

---

# 📦 Instalação da Aplicação

## Pré-requisitos

* Ubuntu 22.04 ou superior
* Acesso à internet
* Permissão de administrador (sudo)

---

## 1. Clonar o repositório

```bash
git clone https://github.com/jardim-ana/Incluime.git

cd Incluime
```

---

## 2. Conceder permissão ao script

```bash
chmod +x install.sh
```

---

## 3. Executar a instalação

```bash
./install.sh
```

O script realiza automaticamente:

* Instalação do Git;
* Instalação do Docker;
* Instalação do Docker Compose;
* Download das imagens da aplicação;
* Inicialização dos containers.

---

## 4. Verificar containers

```bash
docker ps -a
```

Resultado esperado:

```text
app   Up
bd    Up (healthy)
etl   Exited (0)
```

---

## 5. Descobrir o IP da máquina

```bash
curl ifconfig.me
```

---

## 6. Acessar a aplicação

```text
http://IP_PUBLICO:3000
```

---

# 📊 Impacto Esperado

O Conecta+ busca:

* 📈 Reduzir casos de exclusão escolar;
* 📊 Gerar dados confiáveis sobre educação inclusiva;
* 🤝 Aproximar famílias e escolas;
* ⚖️ Fortalecer o cumprimento da legislação;
* 🧩 Promover inclusão efetiva e permanente.

---

# 🔐 Diferenciais

* Centralização de dados;
* Transparência das informações;
* Tecnologia acessível;
* Apoio à gestão escolar;
* Foco em impacto social.

> "Inclusão que transforma."

---

# 👥 Equipe

* Ana Luisa Santos Jardim
* Arthur Bispo dos Santos
* Enrico Assef Antonucci Ferreira
* Felipe Alves de Souza
* Gabriel Medeiros Nascimento
* Lucas Santos Máximo

---

# 📄 Licença

Este projeto está licenciado sob a licença MIT.
