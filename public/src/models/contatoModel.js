var database = require("../database/config");

function inserir(nome, email, tipoContato, mensagem) {
  
  var instrucaoSql = `INSERT INTO mensagens_contate (nome, email, tipo_contato, mensagem) VALUES ("${nome}", "${email}", ${tipoContato}, "${mensagem}")`;

  console.log("Executando a instrução SQL: \n" + instrucaoSql);
  return database.executar(instrucaoSql);
}


module.exports = {
  inserir
}
