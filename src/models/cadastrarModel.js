var database = require("../database/config");

function log(mensagem, nivel) {
    var instrucao = `
        INSERT INTO logss (mensagem, tipo)
        VALUES ('${mensagem}', '${nivel}');
    `;
    return database.executar(instrucao);
}

function cadastrar(nome, sobrenome, email, senha, usuario, escola) {

    console.log("Iniciando cadastro de usuário...");

    var instrucao = `
        INSERT INTO usuario (nome, sobrenome, email, senha, tipo_usuario)
        VALUES ('${nome}', '${sobrenome}', '${email}', '${senha}', '${usuario}');
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

module.exports = {
    cadastrar
};