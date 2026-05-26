var database = require("../database/config")

function log(mensagem, nivel) {
    var instrucao = `
        INSERT INTO logs_sistema (acao, tipo)
        VALUES ('${mensagem}', '${nivel}');
    `;
    return database.executar(instrucao);
}

function autenticar(email, senha) {

    console.log("Autenticando usuário...");

    var instrucaoSql = `
    SELECT 
        u.id,
        u.nome,
        u.sobrenome,
        u.email,
        u.tipo_usuario,
        u.id_escola,

        e.nome_escola

    FROM usuario AS u

    LEFT JOIN escola AS e
        ON u.id_escola = e.id

    WHERE u.email = '${email}'
    AND u.senha = '${senha}';
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

function atualizar(id, nome, sobrenome, email) {

    var instrucaoSql = `
        UPDATE usuario 
        SET 
            nome = '${nome}',
            sobrenome = '${sobrenome}',
            email = '${email}'
        WHERE id = ${id};
    `;

    console.log("Executando a instrução SQL: \n" + instrucaoSql);

    return database.executar(instrucaoSql);
}

function deletar(id) {

    var instrucaoSql = `
        DELETE FROM usuario
        WHERE id = ${id};
    `;

    console.log("Executando a instrução SQL: \n" + instrucaoSql);

    return database.executar(instrucaoSql);
}

module.exports = {
    autenticar,
    atualizar,
    deletar
};