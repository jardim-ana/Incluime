const database = require("../database/config");

function buscarPorEscola(idEscola) {

    const instrucao = `

        SELECT
            *
        FROM meta
        WHERE escola_id = ${idEscola}

    `;

    return database.executar(instrucao);
}

function cadastrar(idEscola, metaMatricula, metaAcessibilidade) {

    const instrucao = `

        INSERT INTO meta (
            escola_id,
            meta_matricula,
            meta_acessibilidade
        ) VALUES (
            ${idEscola},
            ${metaMatricula},
            ${metaAcessibilidade}
        )

    `;

    return database.executar(instrucao);
}

function atualizar(idEscola, metaMatricula, metaAcessibilidade) {

    const instrucao = `

        UPDATE meta
        SET
            meta_matricula = ${metaMatricula},
            meta_acessibilidade = ${metaAcessibilidade}
        WHERE escola_id = ${idEscola}

    `;

    return database.executar(instrucao);
}

function deletar(idEscola) {

    const instrucao = `

        UPDATE meta
        SET
            meta_matricula = 10.0,
            meta_acessibilidade = 70.0
        WHERE escola_id = ${idEscola};

    `;

    return database.executar(instrucao);
}
module.exports = {
    buscarPorEscola,
    cadastrar,
    atualizar,
    deletar
};