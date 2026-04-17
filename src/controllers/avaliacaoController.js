var avaliacaoModel = require("../models/avaliacaoModel");

function cadastrar(req, res) {
    var nota = req.body.notaServer;
    var comentario = req.body.comentarioServer;
    var idUsuario = req.body.idUsuarioServer;

    if (nota == undefined) {
        res.status(400).send("Sua nota está indefinida!");
    }
    else if (comentario == undefined) {
        res.status(400).send("Seu comentario está indefinido!");
    }
    else if (idUsuario == undefined) {
        res.status(400).send("Seu idUsuario está indefinido!");
    }
    else {
        avaliacaoModel.cadastrar(nota, comentario, idUsuario)
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
    