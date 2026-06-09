const dashboardModel =
    require("../models/dashboardModel");

async function buscarMunicipios(
    req,
    res
) {

    try {

        const resultado =
            await dashboardModel
                .buscarMunicipios();

        res.json(resultado);

    } catch (erro) {

        console.log(erro);

        res.status(500).json({
            erro:
                "Erro ao buscar municípios"
        });
    }
}

async function buscarRedes(
    req,
    res
) {

    try {

        const municipio =
            req.params.municipio;

        const resultado =
            await dashboardModel
                .buscarRedes(
                    municipio
                );

        res.json(resultado);

    } catch (erro) {

        console.log(erro);

        res.status(500).json({
            erro:
                "Erro ao buscar redes"
        });
    }
}

async function buscarEscolas(
    req,
    res
) {

    try {

        const municipio =
            req.params.municipio;

        const rede =
            req.params.rede;

        const deficiencia =
            req.params.deficiencia;

        const resultado =
            await dashboardModel
                .buscarEscolas(

                    municipio,
                    rede,
                    deficiencia

                );

        res.json(resultado);

    } catch (erro) {

        console.log(erro);

        res.status(500).json({
            erro:
                "Erro ao buscar escolas"
        });
    }
}

async function buscarDashboard(
    req,
    res
) {

    try {

        const escolaId =
            req.params.escolaId;

        const dashboard =
            await dashboardModel
                .buscarDashboard(
                    escolaId
                );

        const historico =
            await dashboardModel
                .buscarHistorico(
                    escolaId
                );

        dashboard.historico =
            historico;

        res.json(dashboard);

    } catch (erro) {

        console.log(erro);

        res.status(500).json({
            erro:
                "Erro ao buscar dashboard"
        });
    }
}

async function buscarRanking(
    req,
    res
) {

    try {

        const municipio =
            req.params.municipio;

        const rede =
            req.params.rede;

        const deficiencia =
            req.params.deficiencia;

        const resultado =
            await dashboardModel
                .buscarRanking(

                    municipio,
                    rede,
                    deficiencia

                );

        res.json(resultado);

    } catch (erro) {

        console.log(erro);

        res.status(500).json({
            erro:
                "Erro ao buscar ranking"
        });
    }
}

async function buscarComentarios(
    req,
    res
) {

    try {

        const escolaId =
            req.params.escolaId;

        const resultado =
            await dashboardModel
                .buscarComentarios(
                    escolaId
                );

        res.json(resultado);

    } catch (erro) {

        console.log(erro);

        res.status(500).json({
            erro:
                "Erro ao buscar comentários"
        });
    }
}

async function buscarEscolaPorId(
    req,
    res
) {

    try {

        const escolaId =
            req.params.escolaId;

        const resultado =
            await dashboardModel
                .buscarEscolaPorId(
                    escolaId
                );

        res.json(resultado);

    } catch (erro) {

        console.log(erro);

        res.status(500).json({
            erro:
                "Erro ao buscar escola"
        });
    }
}

async function buscarDeficiencias(
    req,
    res
) {

    const municipio =
        req.params.municipio;

    const rede =
        req.params.rede;

    const resultado =
        await dashboardModel
            .buscarDeficiencias(
                municipio,
                rede
            );

    res.json(resultado);
}



module.exports = {

    buscarMunicipios,
    buscarRedes,
    buscarEscolas,
    buscarDashboard,
    buscarRanking,
    buscarComentarios,
    buscarEscolaPorId,
    buscarDeficiencias
};