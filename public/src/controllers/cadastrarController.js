var cadastrarModel = require("../models/cadastrarModel.js");

function cadastrar(req, res) {
    var nome = req.body.nomeServer;
    var sobrenome = req.body.sobrenomeServer;
    var email = req.body.emailServer;
    var senha = req.body.senhaServer;
    var usuario = req.body.usuarioServer;
    var escola = req.body.escolaServer;
    
    if (nome == undefined) {
        res.status(400).send("Seu nome está indefinido!");
    }
    else if (sobrenome == undefined) {
        res.status(400).send("Seu sobrenome está indefinido!");
    }
    else if (email == undefined) {
        res.status(400).send("Seu email está indefinido!")
    }
    else if (senha == undefined) {
        res.status(400).send("Seu senha está indefinido!");
    }
    else if (usuario == undefined) {
        res.status(400).send("Seu usuario está indefinido!");
    }
    else {
        cadastrarModel.cadastrar(nome, sobrenome, email, senha, usuario, escola)
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
    cadastrar
};