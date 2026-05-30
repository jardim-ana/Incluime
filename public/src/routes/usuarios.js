var express = require("express");
var router = express.Router();

var usuarioController = require("../controllers/usuarioController");

router.post("/autenticar", function (req, res) {
    usuarioController.autenticar(req, res);
});

router.put("/atualizar", function (req, res) {
    usuarioController.atualizar(req, res);
});

router.delete("/deletar", function (req, res) {
    usuarioController.deletar(req, res);
});

router.put("/notificacao-email", function (req, res) {
    usuarioController.atualizarNotificacaoEmail(req, res);
});

module.exports = router;