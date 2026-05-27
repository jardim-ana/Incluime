var database = require("../database/config");

function log(mensagem, nivel) {
    var instrucao = `
        INSERT INTO logs_sistema (acao, tipo)
        VALUES ('${mensagem}', '${nivel}');
    `;
    return database.executar(instrucao);
}

function cadastrar(nome, sobrenome, email, senha, usuario, escola) {

    console.log("Iniciando cadastro de usuário...");

    var instrucao = `
        INSERT INTO usuario (nome, sobrenome, email, senha, tipo_usuario, id_escola)
        VALUES ('${nome}', '${sobrenome}', '${email}', '${senha}', '${usuario}', '${escola}');
    `;

    console.log("Executando instrução: \n" + instrucao);

    return database.executar(instrucao)
        .then(resultado => {

            return log(
                `Usuário cadastrado com sucesso: ${email}`,
                "INFO"
            ).then(() => resultado);

        })
        .catch(erro => {

            log(
                `Erro ao cadastrar usuário (${email}): ${erro.message}`,
                "ERRO"
            );

            throw erro;
        });
}

function listarEscolas() {

    var instrucaoSql = `
        SELECT id, nome_escola
        FROM escola;
    `;

    console.log("Executando SQL:");
    console.log(instrucaoSql);

    return database.executar(instrucaoSql);
}

module.exports = {
    cadastrar,
    listarEscolas
};