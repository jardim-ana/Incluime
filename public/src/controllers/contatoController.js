var contatoModel = require("../models/contatoModel.js");

function inserir(req, res) {
    var nome = req.body.nome;
    var email = req.body.email;
    var tipoContato = req.body.tipoContato;
    var mensagem = req.body.mensagem;
    
    if (nome == undefined) {
        res.status(400).send("Seu nome está indefinido!");
    }
    else if (email == undefined) {
        res.status(400).send("Seu email está indefinido!")
    }
    else if (tipoContato == undefined) {
        res.status(400).send("Categoria está indefinida!");
    }
    else if (mensagem == undefined) {
        res.status(400).send("Mensagem está indefinida!");
    }
    else {
        contatoModel.inserir(nome, email, tipoContato, mensagem)
            .then(
                function(resultado) {
                    res.json(resultado);
                }
            ).catch(
                function(erro) {
                    console.log(erro);
                    console.log(
                        "\nHouve um erro no banco ao realizar o cadastro! Erro:",
                        erro.sqlMessage
                    );
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }
}

module.exports = {
    inserir
};