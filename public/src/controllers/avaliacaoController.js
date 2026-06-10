var avaliacaoModel = require("../models/avaliacaoModel");
const { execFile } = require("child_process");

function cadastrar(req, res) {
    var comentario = req.body.comentarioServer;
    var nota = req.body.notaServer;
    var idUsuario = req.body.idUsuarioServer;
    var idEscola = req.body.idEscolaServer;

    if (nota == undefined) {
        res.status(400).send("Sua nota está indefinida!");
    }
    else if (comentario == undefined) {
        res.status(400).send("Seu comentario está indefinido!");
    }
    else if (idUsuario == undefined) {
        res.status(400).send("Seu idUsuario está indefinido!");
    }
    else if (idEscola == undefined) {
        res.status(400).send("Seu idEscola está indefinido!");
    }
    else {
        avaliacaoModel.cadastrar(comentario, nota, idUsuario, idEscola)
            .then(function (resultado) {

                res.json(resultado);
            })
            .catch(function (erro) {
                console.log(erro);
                console.log(
                    "\nHouve um erro no banco ao realizar o cadastro! Erro:",
                    erro.sqlMessage
                );
                res.status(500).json(erro.sqlMessage);
            });
    }
}

var avaliacaoModel = require("../models/avaliacaoModel");

function buscar(req, res) {

    var idUsuario = req.params.idUsuario;

    if (idUsuario == undefined) {

        res.status(400).send("O id do usuário está undefined!");

    } else {

        avaliacaoModel.buscar(idUsuario)
            .then(function (resultado) {
                res.json(resultado);
            })
            .catch(function (erro) {

                console.log(erro);

                console.log(
                    "\nHouve um erro ao buscar a avaliação! Erro: ",
                    erro.sqlMessage
                );

                res.status(500).json(erro.sqlMessage);
            });
    }
}

function atualizar(req, res) {

    var nota = req.body.notaServer;
    var descricao = req.body.descricaoServer;
    var idUsuario = req.body.idUsuarioServer;

    if (nota == undefined) {
        res.status(400).send("Nota undefined");
        return;
    }

    if (descricao == undefined) {
        res.status(400).send("Descrição undefined");
        return;
    }

    if (idUsuario == undefined) {
        res.status(400).send("ID undefined");
        return;
    }

    avaliacaoModel.atualizar(nota, descricao, idUsuario)
        .then(function (resultado) {
            res.json(resultado);
        })
        .catch(function (erro) {

            console.log(erro);

            res.status(500).json(erro.sqlMessage);
        });
}

function deletar(req, res) {

    var idUsuario = req.body.idUsuarioServer;

    if (idUsuario == undefined) {
        res.status(400).send("ID undefined");
        return;
    }

    avaliacaoModel.deletar(idUsuario)
        .then(function (resultado) {
            res.json(resultado);
        })
        .catch(function (erro) {

            console.log(erro);

            res.status(500).json(erro.sqlMessage);
        });
}
module.exports = {
    cadastrar,
    buscar,
    atualizar,
    deletar
};