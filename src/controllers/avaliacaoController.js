var avaliacaoModel = require("../models/avaliacaoModel");
const { execFile } = require("child_process");

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
            .then(function(resultado) {

                enviarNotificacaoSlack(nota, comentario, idUsuario);

                res.json(resultado);
            })
            .catch(function(erro) {
                console.log(erro);
                console.log(
                    "\nHouve um erro no banco ao realizar o cadastro! Erro:",
                    erro.sqlMessage
                );
                res.status(500).json(erro.sqlMessage);
            });
    }
}

function enviarNotificacaoSlack(nota, comentario, idUsuario) {
    const caminhoJar = "ETL-ApachePOI/target/etl-apachepoi-1.0-SNAPSHOT.jar";

    execFile(
        "java",
        [
            "-cp",
            caminhoJar,
            "incluime.conectamais.NotificacaoSlack",
            String(nota),
            comentario || "Sem comentário",
            String(idUsuario)
        ],
        function(error, stdout, stderr) {
            if (error) {
                console.log("Erro ao chamar Java para enviar Slack:", error.message);
                return;
            }

            if (stderr) {
                console.log("Erro no Java/Slack:", stderr);
            }

            console.log("Retorno Java/Slack:", stdout);
        }
    );
}

module.exports = {
    cadastrar
};