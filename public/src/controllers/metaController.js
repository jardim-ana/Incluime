const metaModel =
    require("../models/metaModel");

async function buscarPorEscola(req, res) {

    const idEscola =
        req.params.idEscola;

    try {

        const resultado =
            await metaModel.buscarPorEscola(
                idEscola
            );

        res.status(200).json(
            resultado
        );

    } catch (erro) {

        console.log(erro);

        res.status(500).json({
            erro: erro.sqlMessage
        });
    }
}

async function cadastrar(req, res) {

    const {
        idEscola,
        metaMatricula,
        metaAcessibilidade
    } = req.body;

    try {

        const resultado =
            await metaModel.cadastrar(
                idEscola,
                metaMatricula,
                metaAcessibilidade
            );

        res.status(200).json(
            resultado
        );

    } catch (erro) {

        console.log(erro);

        res.status(500).json({
            erro: erro.sqlMessage
        });
    }
}

async function atualizar(req, res) {

    const idEscola =
        req.params.idEscola;

    const {
        metaMatricula,
        metaAcessibilidade
    } = req.body;

    try {

        const resultado =
            await metaModel.atualizar(
                idEscola,
                metaMatricula,
                metaAcessibilidade
            );


        const existente = await metaModel.buscarPorEscola(idEscola);

        if (existente.length == 0) {

            resultado = await metaModel.cadastrar(
                idEscola,
                metaMatricula,
                metaAcessibilidade
            );

        } else {

            resultado = await metaModel.atualizar(
                idEscola,
                metaMatricula,
                metaAcessibilidade
            );
        }

        res.status(200).json(
            resultado
        );

    } catch (erro) {

        console.log(erro);

        res.status(500).json({
            erro: erro.sqlMessage
        });
    }
}

async function deletar(req, res) {

    const idEscola =
        req.params.idEscola;

    try {

        const resultado =
            await metaModel.deletar(
                idEscola
            );

        res.status(200).json(
            resultado
        );

    } catch (erro) {

        console.log(erro);

        res.status(500).json({
            erro: erro.sqlMessage
        });
    }
}

module.exports = {
    buscarPorEscola,
    cadastrar,
    atualizar,
    deletar
};