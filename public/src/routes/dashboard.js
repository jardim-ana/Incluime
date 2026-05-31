const express =
    require("express");

const router =
    express.Router();

const dashboardController =
    require(
        "../controllers/dashboardController"
    );

router.get(

    "/municipios",

    dashboardController
        .buscarMunicipios
);

router.get(

    "/redes/:municipio",

    dashboardController
        .buscarRedes
);

router.get(

    "/escolas/:municipio/:rede",

    dashboardController
        .buscarEscolas
);

router.get(

    "/dashboard/:escolaId",

    dashboardController
        .buscarDashboard
);

router.get(

    "/ranking/:municipio",

    dashboardController
        .buscarRanking
);

router.get(

    "/comentarios/:escolaId",

    dashboardController
        .buscarComentarios
);

router.get(

    "/escola/:escolaId",

    dashboardController
        .buscarEscolaPorId
);

router.get(

    "/escolas/:municipio/:rede/:deficiencia",

    dashboardController
        .buscarEscolas
);

router.get(

    "/ranking/:municipio/:rede/:deficiencia",

    dashboardController
        .buscarRanking
);

router.get(

    "/deficiencias/:municipio/:rede",

    dashboardController
        .buscarDeficiencias
);

module.exports =
    router;