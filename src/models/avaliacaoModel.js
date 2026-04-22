var database = require("../database/config");

function cadastrar(comentario, nota, idUsuario) {
    console.log("ACESSEI O USUARIO MODEL \n \n\t\t >> Se aqui der erro de 'Error: connect ECONNREFUSED',\n \t\t >> verifique suas credenciais de acesso ao banco\n \t\t >> e se o servidor de seu BD está rodando corretamente. \n\n function cadastrar():", comentario, nota, idUsuario);

    var instrucao = `
        INSERT INTO avaliacao (descricao, nota, dtComentario, usuario_id, escola_id) VALUES ('${nota}', '${comentario}', default, '${idUsuario}', null);
    `;
    console.log("executando instrução: \n" + instrucao);
    return database.executar(instrucao);
}

module.exports = {
    cadastrar
};