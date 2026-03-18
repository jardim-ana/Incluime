var express = require("express");
var router = express.Router();

var cadastrarController = require("../controllers/cadastrarController");

router.post("/cadastrar", function(req, res) {
    cadastrarController.cadastrar(req, res);
});

module.exports = router;