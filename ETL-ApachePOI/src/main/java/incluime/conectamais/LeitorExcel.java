package incluime.conectamais;

import incluime.conectamais.client.S3Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class LeitorExcel {

    private static final String SQL_INSERT =
            "INSERT INTO escola_acessibilidade (ano, sigla_uf, id_municipio, id_municipio_nome, id_escola, rede, tipo_categoria, tipo_localizacao, " +
                    "banheiro_pne, dependencia_pne, corrimao, elevador, pisos_tateis, vao_livre, rampas, sinais_sonoros, sinal_tatil, sinal_visual, acessibilidade_inexistente, " +
                    "qtd_sala_util_acessivel, material_pedago_surdo, qtd_matricula_educ_basica, qtd_matricula_especial, qtd_docente_educ_basica, qtd_turma_especial, qtd_turma_especial_comum, qtd_turma_especial_exclusiva) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final int BATCH_SIZE = 1000;

    public void extrairEscolas(String nomeArquivo, JdbcTemplate template) {

        DataFormatter formatter = new DataFormatter();
        List<Object[]> batch = new ArrayList<>();

        try (
                InputStream arquivo = S3Service.getArquivo(nomeArquivo);
                Workbook workbook = WorkbookFactory.create(arquivo)
        ) {

            System.out.printf("Lendo arquivo do S3: %s%n", nomeArquivo);

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    printarCabecalho(row);
                    continue;
                }

                try {
                    Integer ano = parseInt(formatter, getCell(row, 0));
                    String uf = formatter.formatCellValue(getCell(row, 1));
                    Integer idMuni = parseInt(formatter, getCell(row, 2));
                    String muniNome = formatter.formatCellValue(getCell(row, 3));
                    String idEscola = formatter.formatCellValue(getCell(row, 4));
                    String rede = formatter.formatCellValue(getCell(row, 5));
                    String tipoCategoria = formatter.formatCellValue(getCell(row, 6));
                    String tipoLocalizacao = formatter.formatCellValue(getCell(row, 7));

                    Integer banheiroPne = parseInt(formatter, getCell(row, 8));
                    Integer dependenciaPne = parseInt(formatter, getCell(row, 9));
                    Integer corrimao = parseInt(formatter, getCell(row, 10));
                    Integer elevador = parseInt(formatter, getCell(row, 11));
                    Integer pisoTatil = parseInt(formatter, getCell(row, 12));
                    Integer vaoLivre = parseInt(formatter, getCell(row, 13));
                    Integer rampas = parseInt(formatter, getCell(row, 14));
                    Integer sinalSonoro = parseInt(formatter, getCell(row, 15));
                    Integer sinalTatil = parseInt(formatter, getCell(row, 16));
                    Integer sinalVisual = parseInt(formatter, getCell(row, 17));
                    Integer acessibilidadeInex = parseInt(formatter, getCell(row, 18));
                    Integer qtdSalaUtilAcessivel = parseInt(formatter, getCell(row, 19));
                    Integer materialPedagoSurdo = parseInt(formatter, getCell(row, 20));
                    Integer qtdMatriculaEducBasica = parseInt(formatter, getCell(row, 21));
                    Integer qtdMatriculaEspecial = parseInt(formatter, getCell(row, 22));
                    Integer qtdDocenteEducBasica = parseInt(formatter, getCell(row, 23));
                    Integer qtdTurmaEspecial = parseInt(formatter, getCell(row, 24));
                    Integer qtdTurmaEspecialComum = parseInt(formatter, getCell(row, 25));
                    Integer qtdTurmaEspecialExclusiva = parseInt(formatter, getCell(row, 26));

                    batch.add(new Object[]{
                            ano, uf, idMuni, muniNome, idEscola, rede, tipoCategoria, tipoLocalizacao,
                            banheiroPne, dependenciaPne, corrimao, elevador, pisoTatil, vaoLivre,
                            rampas, sinalSonoro, sinalTatil, sinalVisual, acessibilidadeInex,
                            qtdSalaUtilAcessivel, materialPedagoSurdo, qtdMatriculaEducBasica,
                            qtdMatriculaEspecial, qtdDocenteEducBasica, qtdTurmaEspecial,
                            qtdTurmaEspecialComum, qtdTurmaEspecialExclusiva
                    });

                    // executa batch
                    if (batch.size() == BATCH_SIZE) {
                        template.batchUpdate(SQL_INSERT, batch);
                        batch.clear();
                        System.out.println("Batch inserido: " + BATCH_SIZE + " registros");
                    }

                } catch (Exception e) {
                    System.out.println("Erro na linha " + row.getRowNum() + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }

            // insere restante
            if (!batch.isEmpty()) {
                template.batchUpdate(SQL_INSERT, batch);
                System.out.println("Batch final inserido: " + batch.size() + " registros");
            }

            System.out.println("Leitura finalizada");

        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo do S3: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Cell getCell(Row row, int index) {
        return row.getCell(index, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
    }

    private Integer parseInt(DataFormatter formatter, Cell cell) {
        try {
            String valor = formatter.formatCellValue(cell).trim();
            if (valor.isEmpty()) return null;
            return (int) Double.parseDouble(valor);
        } catch (Exception e) {
            return null;
        }
    }

    private void printarCabecalho(Row row) {
        System.out.println("----- CABECALHO -----");
        for (int i = 0; i < row.getLastCellNum(); i++) {
            System.out.println("Coluna " + i + ": " + row.getCell(i));
        }
    }
}