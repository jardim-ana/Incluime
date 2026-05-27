const express =
    require("express");

const router =
    express.Router();

const metaController =
    require("../controllers/metaController");

router.get(
    "/:idEscola",
    metaController.buscarPorEscola
);

router.post(
    "/",
    metaController.cadastrar
);

router.put(
    "/:idEscola",
    metaController.atualizar
);

router.delete(
    "/:idEscola",
    metaController.deletar
);

module.exports = router;