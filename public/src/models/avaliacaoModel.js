var database = require("../database/config");

function cadastrar(comentario, nota, idUsuario, idEscola) {
    console.log("ACESSEI O USUARIO MODEL \n \n\t\t >> Se aqui der erro de 'Error: connect ECONNREFUSED',\n \t\t >> verifique suas credenciais de acesso ao banco\n \t\t >> e se o servidor de seu BD está rodando corretamente. \n\n function cadastrar():", comentario, nota, idUsuario);

    var instrucao = `
        INSERT INTO avaliacao (descricao, nota, dtComentario, usuario_id, escola_id) VALUES ('${comentario}', '${nota}', default, '${idUsuario}', '${idEscola}');
    `;
    console.log("executando instrução: \n" + instrucao);
    return database.executar(instrucao);
}

function buscar(idUsuario) {

    var instrucaoSql = `
        SELECT nota, descricao
        FROM avaliacao
        WHERE usuario_id = ${idUsuario};
    `;

    console.log("Executando a instrução SQL: \n" + instrucaoSql);

    return database.executar(instrucaoSql);
}

function atualizar(nota, descricao, idUsuario) {

    var instrucaoSql = `
        UPDATE avaliacao
        SET
            nota = ${nota},
            descricao = '${descricao}'
        WHERE usuario_id = ${idUsuario};
    `;

    console.log(instrucaoSql);

    return database.executar(instrucaoSql);
}

function deletar(idUsuario) {

    var instrucaoSql = `
        DELETE FROM avaliacao
        WHERE usuario_id = ${idUsuario};
    `;

    console.log(instrucaoSql);

    return database.executar(instrucaoSql);
}

module.exports = {
    cadastrar,
    buscar,
    atualizar,
    deletar
};