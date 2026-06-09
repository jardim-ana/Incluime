const database =
    require("../database/config");

async function buscarMunicipios() {

    const instrucaoSql = `

        SELECT DISTINCT
            id_municipio_nome

        FROM
            base_dados_censo_escolar

        ORDER BY
            id_municipio_nome;

    `;

    const resultado =
        await database.executar(
            instrucaoSql
        );

    return resultado;
}

async function buscarRedes(
    municipio
) {

    const instrucaoSql = `

        SELECT DISTINCT
            rede

        FROM
            base_dados_censo_escolar

        WHERE
            id_municipio_nome = '${municipio}'
        ORDER BY
            rede;

    `;

    const resultado =
        await database.executar(
            instrucaoSql
        );

    return resultado;
}

async function buscarEscolas(
    municipio,
    rede,
    deficiencia
) {

    const instrucaoSql = `

        SELECT DISTINCT

            e.id,
            e.nome_escola

        FROM escola e

        INNER JOIN
            base_dados_censo_escolar b

        ON b.escola_id = e.id

        INNER JOIN
            escola_deficiencia ed

        ON ed.escola_id = e.id

        INNER JOIN
            tipo_deficiencia td

        ON td.id =
            ed.deficiencia_id

        WHERE

            b.id_municipio_nome =
                '${municipio}'

            AND

            b.rede =
                '${rede}'

            AND

            td.nome =
                '${deficiencia}'

        ORDER BY
            e.nome_escola;

    `;

    return await database.executar(
        instrucaoSql
    );
}

async function buscarDashboard(
    escolaId
) {

    const instrucaoSql = `

        SELECT

            e.id,
            e.nome_escola,

            q.quantidade_matricula_educacao_basica,

            (
                (
                    IFNULL(a.acessibilidade_corrimao, 0) +
                    IFNULL(a.acessibilidade_elevador, 0) +
                    IFNULL(a.acessibilidade_pisos_tateis, 0) +
                    IFNULL(a.acessibilidade_vao_livre, 0) +
                    IFNULL(a.acessibilidade_rampas, 0) +
                    IFNULL(a.acessibilidade_sinais_sonoros, 0) +
                    IFNULL(a.acessibilidade_sinal_tatil, 0) +
                    IFNULL(a.acessibilidade_sinal_visual, 0)
                ) / 8
            ) * 100
            AS indice_acessibilidade,

            ROUND(
                AVG(av.nota),
                1
            ) AS media_avaliacao,

            a.acessibilidade_corrimao,
            a.acessibilidade_elevador,
            a.acessibilidade_pisos_tateis,
            a.acessibilidade_vao_livre,
            a.acessibilidade_rampas,
            a.acessibilidade_sinais_sonoros,
            a.acessibilidade_sinal_tatil,
            a.acessibilidade_sinal_visual,
            a.acessibilidade_inexistente,

            b.id_municipio_nome,
            b.rede

        FROM escola e

        LEFT JOIN
            base_dados_quantidades q

        ON q.escola_id = e.id

        LEFT JOIN
            base_dados_censo_escolar b

        ON b.escola_id = e.id

        LEFT JOIN
            base_dados_acessibilidade a

        ON a.censo_escolar_id = b.id

        LEFT JOIN
            avaliacao av

        ON av.escola_id = e.id

        WHERE
            e.id = '${escolaId}'

        GROUP BY

            e.id,
            e.nome_escola,

            q.quantidade_matricula_educacao_basica,

            a.acessibilidade_corrimao,
            a.acessibilidade_elevador,
            a.acessibilidade_pisos_tateis,
            a.acessibilidade_vao_livre,
            a.acessibilidade_rampas,
            a.acessibilidade_sinais_sonoros,
            a.acessibilidade_sinal_tatil,
            a.acessibilidade_sinal_visual,
            a.acessibilidade_inexistente,

            b.id_municipio_nome,
            b.rede;

    `;

    console.log(instrucaoSql);

    const resultado =
        await database.executar(
            instrucaoSql
        );

    return resultado[0];
}

async function buscarRanking(
    municipio,
    rede,
    deficiencia
) {

    let instrucaoSql = "";

    if (
        rede &&
        deficiencia
    ) {

        instrucaoSql = `

            SELECT

                e.id,
                e.nome_escola,

                ROUND(

                    (

                        (

                            IFNULL(a.acessibilidade_corrimao, 0)

                            +

                            IFNULL(a.acessibilidade_elevador, 0)

                            +

                            IFNULL(a.acessibilidade_pisos_tateis, 0)

                            +

                            IFNULL(a.acessibilidade_vao_livre, 0)

                            +

                            IFNULL(a.acessibilidade_rampas, 0)

                            +

                            IFNULL(a.acessibilidade_sinais_sonoros, 0)

                            +

                            IFNULL(a.acessibilidade_sinal_tatil, 0)

                            +

                            IFNULL(a.acessibilidade_sinal_visual, 0)

                        ) / 8

                    ) * 100,

                    1

                ) AS indice_acessibilidade

            FROM escola e

            INNER JOIN
                base_dados_censo_escolar b

            ON b.escola_id = e.id

            INNER JOIN
                base_dados_acessibilidade a

            ON a.censo_escolar_id = b.id

            INNER JOIN
                escola_deficiencia ed

            ON ed.escola_id = e.id

            INNER JOIN
                tipo_deficiencia td

            ON td.id = ed.deficiencia_id

            WHERE

                b.id_municipio_nome =
                    '${municipio}'

                AND

                b.rede =
                    '${rede}'

                AND

                td.nome =
                    '${deficiencia}'

            ORDER BY
                indice_acessibilidade DESC;

        `;

    } else {

        instrucaoSql = `

            SELECT

                e.id,
                e.nome_escola,

                ROUND(

                    (

                        (

                            IFNULL(a.acessibilidade_corrimao, 0)

                            +

                            IFNULL(a.acessibilidade_elevador, 0)

                            +

                            IFNULL(a.acessibilidade_pisos_tateis, 0)

                            +

                            IFNULL(a.acessibilidade_vao_livre, 0)

                            +

                            IFNULL(a.acessibilidade_rampas, 0)

                            +

                            IFNULL(a.acessibilidade_sinais_sonoros, 0)

                            +

                            IFNULL(a.acessibilidade_sinal_tatil, 0)

                            +

                            IFNULL(a.acessibilidade_sinal_visual, 0)

                        ) / 8

                    ) * 100,

                    1

                ) AS indice_acessibilidade

            FROM escola e

            INNER JOIN
                base_dados_censo_escolar b

            ON b.escola_id = e.id

            INNER JOIN
                base_dados_acessibilidade a

            ON a.censo_escolar_id = b.id

            WHERE

                b.id_municipio_nome =
                    '${municipio}'

            ORDER BY
                indice_acessibilidade DESC;

        `;
    }

    console.log(instrucaoSql);

    return await database.executar(
        instrucaoSql
    );
}

async function buscarComentarios(
    escolaId
) {

    const instrucaoSql = `

        SELECT

            descricao,
            nota,
            dtComentario

        FROM avaliacao

        WHERE
            escola_id = '${escolaId}'

        ORDER BY
            dtComentario DESC;

    `;

    const resultado =
        await database.executar(
            instrucaoSql
        );

    return resultado;
}

async function buscarHistorico(
    escolaId
) {

    const instrucaoSql = `

        SELECT

            ano,
            quantidade_matricula_educacao_basica AS valor

        FROM
            base_dados_quantidades

        WHERE
            escola_id = '${escolaId}'

        ORDER BY
            ano;

    `;

    const resultado =
        await database.executar(
            instrucaoSql
        );

    return Array.isArray(resultado)
        ? resultado
        : [];
}

async function buscarEscolaPorId(
    escolaId
) {

    const instrucaoSql = `

        SELECT

            e.id,
            e.nome_escola,
            b.id_municipio_nome,
            b.rede

        FROM escola e

        INNER JOIN
        base_dados_censo_escolar b

        ON b.escola_id = e.id

        WHERE
        e.id = '${escolaId}'

        LIMIT 1;

    `;

    const resultado =
        await database.executar(
            instrucaoSql
        );

    return resultado[0];
}

async function buscarDeficiencias(
    municipio,
    rede
) {

    const instrucaoSql = `

                SELECT DISTINCT

            td.nome

        FROM tipo_deficiencia td

        INNER JOIN
            escola_deficiencia ed

        ON td.id =
            ed.deficiencia_id

        INNER JOIN
            escola e

        ON e.id =
            ed.escola_id

        INNER JOIN
            base_dados_censo_escolar b

        ON b.escola_id =
            e.id

        WHERE

            b.id_municipio_nome =
                '${municipio}'

            AND

            b.rede =
                '${rede}'

        ORDER BY
            td.nome;

    `;

    const resultado =
        await database.executar(
            instrucaoSql
        );

    return resultado;
}

module.exports = {
    buscarDashboard,
    buscarRanking,
    buscarComentarios,
    buscarMunicipios,
    buscarRedes,
    buscarEscolas,
    buscarHistorico,
    buscarEscolaPorId,
    buscarDeficiencias
};