var database = require("../database/config")

function log(mensagem, nivel) {
    var instrucao = `
        INSERT INTO logss (acao, tipo)
        VALUES ('${mensagem}', '${nivel}');
    `;
    return database.executar(instrucao);
}

function autenticar(email, senha) {

    console.log("Autenticando usuário...");

    var instrucaoSql = `
        SELECT u.id, u.nome, u.sobrenome, u.email, u.nome_escola, u.tipo_usuario
        FROM usuario AS u
        WHERE email = '${email}' AND senha = '${senha}';
    `;

    console.log("Executando SQL: \n" + instrucaoSql);

    return database.executar(instrucaoSql)
        .then(resultado => {

            if (resultado.length > 0) {

                log(
                    `Login bem-sucedido: ${email}`,
                    "INFO"
                );

                return resultado;
            }

            log(
                `Tentativa de login inválida: ${email}`,
                "AVISO"
            );

            return resultado;
        })
        .catch(erro => {

            log(
                `Erro ao autenticar usuário (${email}): ${erro.message}`,
                "ERRO"
            );

            throw erro;
        });
}

module.exports = {
    autenticar
};