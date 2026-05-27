var express = require("express");
var router = express.Router();

var cadastrarController = require("../controllers/cadastrarController");

router.post("/cadastrar", function(req, res) {
    cadastrarController.cadastrar(req, res);
});

router.get("/listarEscolas", function (req, res) {
    cadastrarController.listarEscolas(req, res);
});

module.exports = router;