const database = require("../database/config");

function buscarPorEscola(idEscola) {

    const instrucao = `

        SELECT
            *
        FROM meta
        WHERE fk_escola = ${idEscola}

    `;

    return database.executar(instrucao);
}

function cadastrar(idEscola, metaMatricula, metaAcessibilidade) {

    const instrucao = `

        INSERT INTO meta (
            fk_escola,
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
        WHERE fk_escola = ${idEscola}

    `;

    return database.executar(instrucao);
}

function deletar(idEscola) {

    const instrucao = `

        DELETE FROM meta
        WHERE fk_escola = ${idEscola}

    `;

    return database.executar(instrucao);
}

module.exports = {
    buscarPorEscola,
    cadastrar,
    atualizar,
    deletar
};