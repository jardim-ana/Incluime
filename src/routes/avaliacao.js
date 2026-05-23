var express = require("express");
var router = express.Router();

var avaliacaoController = require("../controllers/avaliacaoController");

router.post("/cadastrar", function(req, res) {
    avaliacaoController.cadastrar(req, res);
});

router.get("/buscar/:idUsuario", function (req, res) {
    avaliacaoController.buscar(req, res);
});

router.put("/atualizar", function (req, res) {
    avaliacaoController.atualizar(req, res);
});

router.delete("/deletar", function (req, res) {
    avaliacaoController.deletar(req, res);
});

module.exports = router;