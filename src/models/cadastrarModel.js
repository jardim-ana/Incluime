var database = require("../database/config");

function cadastrar(nome, sobrenome, email, senha, usuario) {
    console.log("ACESSEI O USUARIO MODEL \n \n\t\t >> Se aqui der erro de 'Error: connect ECONNREFUSED',\n \t\t >> verifique suas credenciais de acesso ao banco\n \t\t >> e se o servidor de seu BD está rodando corretamente. \n\n function cadastrar():", nome, sobrenome, email, senha, usuario);

    var instrucao = `
        INSERT INTO usuario (nome, sobrenome, email, senha, tipo_usuario) VALUES ('${nome}', '${sobrenome}', '${email}', '${senha}', '${usuario}');
    `;
    console.log("executando instrução: \n" + instrucao);
    return database.executar(instrucao);
}

module.exports = {
    cadastrar
};