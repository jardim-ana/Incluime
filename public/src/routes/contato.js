var express = require("express");
var router = express.Router();

var contatoController = require("../controllers/contatoController");


router.post("/inserir", function (req, res) {
  contatoController.inserir(req, res);
})

module.exports = router;