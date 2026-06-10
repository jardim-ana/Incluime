package incluime.conectamais;

import incluime.conectamais.client.S3Service;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeitorExcel extends BaseETL {

    private static final String SQL_INSERT_ENDERECO =
            "INSERT INTO endereco (" +
                    "logradouro, numero, cep" +
                    ") VALUES (?, ?, ?)";

    private static final String SQL_INSERT_ESCOLA =
            "INSERT INTO escola (" +
                    "codigo_inep, nome_escola, telefone, endereco_id" +
                    ") VALUES (?, ?, ?, ?)";

    private static final String SQL_INSERT_CENSO =
            "INSERT INTO base_dados_censo_escolar (" +
                    "ano, sigla_uf, id_municipio, " +
                    "id_municipio_nome, escola_id, rede, " +
                    "tipo_categoria_escola_privada, " +
                    "tipo_localizacao, banheiro_pne, " +
                    "dependencia_pne, material_pedagogico_surdo" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_ACESSIBILIDADE =
            "INSERT INTO base_dados_acessibilidade (" +
                    "censo_escolar_id, acessibilidade_corrimao, " +
                    "acessibilidade_elevador, acessibilidade_pisos_tateis, " +
                    "acessibilidade_vao_livre, acessibilidade_rampas, " +
                    "acessibilidade_sinais_sonoros, " +
                    "acessibilidade_sinal_tatil, " +
                    "acessibilidade_sinal_visual, " +
                    "acessibilidade_inexistente" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_QUANTIDADES =
            "INSERT INTO base_dados_quantidades (" +
                    "ano, escola_id, " +
                    "quantidade_sala_utilizada_acessivel, " +
                    "quantidade_matricula_educacao_basica, " +
                    "quantidade_matricula_especial, " +
                    "quantidade_docente_educacao_basica, " +
                    "quantidade_turma_especial, " +
                    "quantidade_turma_especial_comum, " +
                    "quantidade_turma_especial_exclusiva" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_ESCOLA_DEFICIENCIA =
            "INSERT IGNORE INTO escola_deficiencia " +
                    "(escola_id, deficiencia_id) VALUES (?, ?)";

    private static final String SQL_BUSCAR_DEFICIENCIA =
            "SELECT id FROM tipo_deficiencia WHERE nome = ?";

    private static final String SQL_BUSCAR_ESCOLA =
                "SELECT id FROM escola WHERE codigo_inep = ?";

    private static final String SQL_BUSCAR_ENDERECO =
                "SELECT id FROM endereco " +
                "WHERE logradouro = ? AND numero = ? AND cep = ?";

    public void extrairEscolas(
            String[] nomeArquivo,
            Connection conexao
    ) {

        DataFormatter formatter =
                new DataFormatter();

        List<Escola> listaEscolas =
                new ArrayList<>();

        Map<String, List<Escola>> mapaEscolas =
        new HashMap<>();

        try {

            conexao.setAutoCommit(false);

            log(
                    conexao,
                    "Iniciando ETL",
                    "INFO"
            );

            try (

                    InputStream arquivo =
                            S3Service.getArquivo(
                                    nomeArquivo[0]
                            );

                    Workbook workbook =
                            WorkbookFactory.create(
                                    arquivo
                            )
            ) {

                Sheet sheet =
                        workbook.getSheetAt(0);

                for (Row row : sheet) {

                    if (row.getRowNum() == 0) {
                        continue;
                    }

                    Escola escola =
                            new Escola();

                    escola.setAno(
                            parseInt(
                                    formatter,
                                    getCell(row, 0)
                            )
                    );

                    escola.setSiglaUf(
                            formatter.formatCellValue(
                                    getCell(row, 1)
                            )
                    );

                    escola.setIdMunicipio(
                            parseInt(
                                    formatter,
                                    getCell(row, 2)
                            )
                    );

                    escola.setIdMunicipioNome(
                            formatter.formatCellValue(
                                    getCell(row, 3)
                            )
                    );

                    String codigoInep =
                            formatter.formatCellValue(
                                    getCell(row, 4)
                            );

                    escola.setCodigoInep(
                            codigoInep
                    );

                    escola.setRede(
                            formatter.formatCellValue(
                                    getCell(row, 5)
                            )
                    );

                    escola.setTipoCategoria(
                            formatter.formatCellValue(
                                    getCell(row, 6)
                            )
                    );

                    escola.setTipoLocalizacao(
                            formatter.formatCellValue(
                                    getCell(row, 7)
                            )
                    );

                    escola.setBanheiroPne(
                            parseInt(
                                    formatter,
                                    getCell(row, 8)
                            )
                    );

                    escola.setDependenciaPne(
                            parseInt(
                                    formatter,
                                    getCell(row, 9)
                            )
                    );

                    escola.setCorrimao(
                            parseInt(
                                    formatter,
                                    getCell(row, 10)
                            )
                    );

                    escola.setElevador(
                            parseInt(
                                    formatter,
                                    getCell(row, 11)
                            )
                    );

                    escola.setPisosTateis(
                            parseInt(
                                    formatter,
                                    getCell(row, 12)
                            )
                    );

                    escola.setVaoLivre(
                            parseInt(
                                    formatter,
                                    getCell(row, 13)
                            )
                    );

                    escola.setRampas(
                            parseInt(
                                    formatter,
                                    getCell(row, 14)
                            )
                    );

                    escola.setSinaisSonoros(
                            parseInt(
                                    formatter,
                                    getCell(row, 15)
                            )
                    );

                    escola.setSinalTatil(
                            parseInt(
                                    formatter,
                                    getCell(row, 16)
                            )
                    );

                    escola.setSinalVisual(
                            parseInt(
                                    formatter,
                                    getCell(row, 17)
                            )
                    );

                    escola.setAcessibilidadeInexistente(
                            parseInt(
                                    formatter,
                                    getCell(row, 18)
                            )
                    );

                    escola.setQtdSalaUtilAcessivel(
                            parseInt(
                                    formatter,
                                    getCell(row, 19)
                            )
                    );

                    escola.setMaterialPedagoSurdo(
                            parseInt(
                                    formatter,
                                    getCell(row, 20)
                            )
                    );

                    escola.setQtdMatriculaEducBasica(
                            parseInt(
                                    formatter,
                                    getCell(row, 21)
                            )
                    );

                    escola.setQtdMatriculaEspecial(
                            parseInt(
                                    formatter,
                                    getCell(row, 22)
                            )
                    );

                    escola.setQtdDocenteEducBasica(
                            parseInt(
                                    formatter,
                                    getCell(row, 23)
                            )
                    );

                    escola.setQtdTurmaEspecial(
                            parseInt(
                                    formatter,
                                    getCell(row, 24)
                            )
                    );

                    escola.setQtdTurmaEspecialComum(
                            parseInt(
                                    formatter,
                                    getCell(row, 25)
                            )
                    );

                    escola.setQtdTurmaEspecialExclusiva(
                            parseInt(
                                    formatter,
                                    getCell(row, 26)
                            )
                    );

                    mapaEscolas.computeIfAbsent(
                                codigoInep,
                                k -> new ArrayList<>()
                        ).add(escola);
                }
            }

            try (

                    InputStream arquivo =
                            S3Service.getArquivo(
                                    nomeArquivo[1]
                            );

                    Workbook workbook =
                            WorkbookFactory.create(
                                    arquivo
                            )
            ) {

                Sheet sheet =
                        workbook.getSheetAt(0);

                for (Row row : sheet) {

                    if (row.getRowNum() == 0) {
                        continue;
                    }

                    String codigoInep =
                            formatter.formatCellValue(
                                    getCell(row, 0)
                            );

                    List<Escola> escolas =
                        mapaEscolas.get(
                                codigoInep
                        );

                    if (escolas != null) {
                        for (Escola escola : escolas) {
                            escola.setNomeEscola(
                                    formatter.formatCellValue(
                                        getCell(row, 1)
                                )
                        );

                        escola.setLogradouro(
                                formatter.formatCellValue(
                                        getCell(row, 2)
                                )
                        );

                        escola.setNumero(
                                formatter.formatCellValue(
                                        getCell(row, 3)
                                )
                        );

                        escola.setCep(
                                formatter.formatCellValue(
                                        getCell(row, 4)
                                )
                        );

                        escola.setTelefone(
                                formatter.formatCellValue(
                                        getCell(row, 5)
                                )
                        );

                           listaEscolas.add(
                            escola
                         );
                    }
                }
            }
        }

            try (

                    PreparedStatement stmtEndereco =
                            conexao.prepareStatement(
                                    SQL_INSERT_ENDERECO,
                                    PreparedStatement.RETURN_GENERATED_KEYS
                            );

                    PreparedStatement stmtEscola =
                            conexao.prepareStatement(
                                    SQL_INSERT_ESCOLA,
                                    PreparedStatement.RETURN_GENERATED_KEYS
                            );

                    PreparedStatement stmtCenso =
                            conexao.prepareStatement(
                                    SQL_INSERT_CENSO,
                                    PreparedStatement.RETURN_GENERATED_KEYS
                            );

                    PreparedStatement stmtAcessibilidade =
                            conexao.prepareStatement(
                                    SQL_INSERT_ACESSIBILIDADE
                            );

                    PreparedStatement stmtQuantidades =
                            conexao.prepareStatement(
                                    SQL_INSERT_QUANTIDADES
                            );

                    PreparedStatement stmtBuscarDeficiencia =
                            conexao.prepareStatement(
                                    SQL_BUSCAR_DEFICIENCIA
                            );

                    PreparedStatement stmtEscolaDeficiencia =
                            conexao.prepareStatement(
                                    SQL_INSERT_ESCOLA_DEFICIENCIA
                            );
                    PreparedStatement stmtBuscarEscola =
                            conexao.prepareStatement(
                                    SQL_BUSCAR_ESCOLA
                            );

                    PreparedStatement stmtBuscarEndereco =
                            conexao.prepareStatement(
                                    SQL_BUSCAR_ENDERECO
                            );


            ) {

                Integer idFisica =
                        buscarIdDeficiencia(
                                stmtBuscarDeficiencia,
                                "Fisica"
                        );

                Integer idVisual =
                        buscarIdDeficiencia(
                                stmtBuscarDeficiencia,
                                "Visual"
                        );

                Integer idAuditiva =
                        buscarIdDeficiencia(
                                stmtBuscarDeficiencia,
                                "Auditiva"
                        );

                Map<String, Integer> cacheEscolas = new HashMap<>();

                Map<String, Integer> cacheEnderecos = new HashMap<>();

                for (Escola escola : listaEscolas) {

                    // ENDEREÇO

                    String chaveEndereco =
                        escola.getLogradouro() + "|" +
                        escola.getNumero() + "|" +
                        escola.getCep();

                Integer enderecoId =
                        cacheEnderecos.get(
                                chaveEndereco
                        );

                if (enderecoId == null) {

                stmtBuscarEndereco.setString(
                        1,
                        escola.getLogradouro()
                );

                stmtBuscarEndereco.setString(
                        2,
                        escola.getNumero()
                );

                stmtBuscarEndereco.setString(
                        3,
                        escola.getCep()
                );

                ResultSet rsEnderecoBusca =
                        stmtBuscarEndereco.executeQuery();

                if (rsEnderecoBusca.next()) {

                        enderecoId =
                                rsEnderecoBusca.getInt(
                                        "id"
                                );

                } else {

                        stmtEndereco.setString(
                                1,
                                escola.getLogradouro()
                        );

                        stmtEndereco.setString(
                                2,
                                escola.getNumero()
                        );

                        stmtEndereco.setString(
                                3,
                                escola.getCep()
                        );

                        stmtEndereco.executeUpdate();

                        ResultSet rsEnderecoNovo =
                                stmtEndereco.getGeneratedKeys();

                        if (rsEnderecoNovo.next()) {

                        enderecoId =
                                rsEnderecoNovo.getInt(
                                        1
                                );
                        }
                }

                cacheEnderecos.put(
                        chaveEndereco,
                        enderecoId
                );
                }

                    // ESCOLA

                Integer escolaId =
                cacheEscolas.get(
                        escola.getCodigoInep()
                );

                if (escolaId == null) {

                stmtBuscarEscola.setString(
                        1,
                        escola.getCodigoInep()
                );

                ResultSet rsBuscaEscola =
                        stmtBuscarEscola.executeQuery();

                if (rsBuscaEscola.next()) {

                        escolaId =
                                rsBuscaEscola.getInt(
                                        "id"
                                );

                } else {

                        stmtEscola.setString(
                                1,
                                escola.getCodigoInep()
                        );

                        stmtEscola.setString(
                                2,
                                escola.getNomeEscola()
                        );

                        stmtEscola.setString(
                                3,
                                escola.getTelefone()
                        );

                        stmtEscola.setInt(
                                4,
                                enderecoId
                        );

                        stmtEscola.executeUpdate();

                        ResultSet rsNovaEscola =
                                stmtEscola.getGeneratedKeys();

                        if (rsNovaEscola.next()) {

                        escolaId =
                                rsNovaEscola.getInt(
                                        1
                                );
                        }
                }

                cacheEscolas.put(
                        escola.getCodigoInep(),
                        escolaId
                );
                }
                    // CENSO

                    stmtCenso.setObject(
                            1,
                            escola.getAno()
                    );

                    stmtCenso.setString(
                            2,
                            escola.getSiglaUf()
                    );

                    stmtCenso.setObject(
                            3,
                            escola.getIdMunicipio()
                    );

                    stmtCenso.setString(
                            4,
                            escola.getIdMunicipioNome()
                    );

                    stmtCenso.setInt(
                            5,
                            escolaId
                    );

                    stmtCenso.setString(
                            6,
                            escola.getRede()
                    );

                    stmtCenso.setString(
                            7,
                            escola.getTipoCategoria()
                    );

                    stmtCenso.setString(
                            8,
                            escola.getTipoLocalizacao()
                    );

                    stmtCenso.setObject(
                            9,
                            escola.getBanheiroPne()
                    );

                    stmtCenso.setObject(
                            10,
                            escola.getDependenciaPne()
                    );

                    stmtCenso.setObject(
                            11,
                            escola.getMaterialPedagoSurdo()
                    );

                    stmtCenso.executeUpdate();

                    ResultSet rsCenso =
                            stmtCenso.getGeneratedKeys();

                    int censoId = 0;

                    if (rsCenso.next()) {

                        censoId =
                                rsCenso.getInt(1);
                    }

                    // ACESSIBILIDADE

                    stmtAcessibilidade.setInt(
                            1,
                            censoId
                    );

                    stmtAcessibilidade.setObject(
                            2,
                            escola.getCorrimao()
                    );

                    stmtAcessibilidade.setObject(
                            3,
                            escola.getElevador()
                    );

                    stmtAcessibilidade.setObject(
                            4,
                            escola.getPisosTateis()
                    );

                    stmtAcessibilidade.setObject(
                            5,
                            escola.getVaoLivre()
                    );

                    stmtAcessibilidade.setObject(
                            6,
                            escola.getRampas()
                    );

                    stmtAcessibilidade.setObject(
                            7,
                            escola.getSinaisSonoros()
                    );

                    stmtAcessibilidade.setObject(
                            8,
                            escola.getSinalTatil()
                    );

                    stmtAcessibilidade.setObject(
                            9,
                            escola.getSinalVisual()
                    );

                    stmtAcessibilidade.setObject(
                            10,
                            escola.getAcessibilidadeInexistente()
                    );

                    stmtAcessibilidade.executeUpdate();

                    boolean fisica =
                            (escola.getCorrimao() != null &&
                                    escola.getCorrimao() == 1)

                                    ||

                                    (escola.getElevador() != null &&
                                            escola.getElevador() == 1)

                                    ||

                                    (escola.getVaoLivre() != null &&
                                            escola.getVaoLivre() == 1)

                                    ||

                                    (escola.getRampas() != null &&
                                            escola.getRampas() == 1)

                                    ||

                                    (escola.getBanheiroPne() != null &&
                                            escola.getBanheiroPne() == 1)

                                    ||

                                    (escola.getDependenciaPne() != null &&
                                            escola.getDependenciaPne() == 1);

                    boolean visual =
                            (escola.getPisosTateis() != null &&
                                    escola.getPisosTateis() == 1)

                                    ||

                                    (escola.getSinaisSonoros() != null &&
                                            escola.getSinaisSonoros() == 1)

                                    ||

                                    (escola.getSinalTatil() != null &&
                                            escola.getSinalTatil() == 1);

                    boolean auditiva =
                            (escola.getSinalVisual() != null &&
                                    escola.getSinalVisual() == 1)

                                    ||

                                    (escola.getMaterialPedagoSurdo() != null &&
                                            escola.getMaterialPedagoSurdo() == 1);

                    if (fisica) {

                        inserirDeficiencia(
                                stmtEscolaDeficiencia,
                                escolaId,
                                idFisica
                        );
                    }

                    if (visual) {

                        inserirDeficiencia(
                                stmtEscolaDeficiencia,
                                escolaId,
                                idVisual
                        );
                    }

                    if (auditiva) {

                        inserirDeficiencia(
                                stmtEscolaDeficiencia,
                                escolaId,
                                idAuditiva
                        );
                    }

                    // QUANTIDADES

                    stmtQuantidades.setObject(
                            1,
                            escola.getAno()
                    );

                    stmtQuantidades.setInt(
                            2,
                            escolaId
                    );

                    stmtQuantidades.setObject(
                            3,
                            escola.getQtdSalaUtilAcessivel()
                    );

                    stmtQuantidades.setObject(
                            4,
                            escola.getQtdMatriculaEducBasica()
                    );

                    stmtQuantidades.setObject(
                            5,
                            escola.getQtdMatriculaEspecial()
                    );

                    stmtQuantidades.setObject(
                            6,
                            escola.getQtdDocenteEducBasica()
                    );

                    stmtQuantidades.setObject(
                            7,
                            escola.getQtdTurmaEspecial()
                    );

                    stmtQuantidades.setObject(
                            8,
                            escola.getQtdTurmaEspecialComum()
                    );

                    stmtQuantidades.setObject(
                            9,
                            escola.getQtdTurmaEspecialExclusiva()
                    );

                    stmtQuantidades.executeUpdate();
                }

                conexao.commit();

                log(
                        conexao,
                        "ETL concluída com sucesso",
                        "INFO"
                );
            }

        } catch (Exception e) {

            try {

                conexao.rollback();

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    private Integer buscarIdDeficiencia(
            PreparedStatement stmt,
            String nome
    ) throws Exception {

        stmt.setString(
                1,
                nome
        );

        ResultSet rs =
                stmt.executeQuery();

        if (rs.next()) {

            return rs.getInt(
                    "id"
            );
        }

        return null;
    }

    private void inserirDeficiencia(
            PreparedStatement stmt,
            Integer escolaId,
            Integer deficienciaId
    ) throws Exception {

        if (deficienciaId == null) {
            return;
        }

        stmt.setInt(
                1,
                escolaId
        );

        stmt.setInt(
                2,
                deficienciaId
        );

        stmt.executeUpdate();
    }
}

