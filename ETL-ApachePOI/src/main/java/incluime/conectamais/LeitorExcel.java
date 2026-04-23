package incluime.conectamais;

import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

public class LeitorExcel {

    private final S3Client s3;

    public LeitorExcel(S3Client s3) {
        this.s3 = s3;
    }

    public List<Escola> extrairEscolas(String bucket, String key) {
        List<Escola> escolasExtraidas = new ArrayList<>();

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        try (
                InputStream arquivo = s3.getObject(request);
                Workbook workbook = new XSSFWorkbook(arquivo)
        ) {

            System.out.printf("Lendo arquivo do S3: s3://%s/%s%n", bucket, key);

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    printarCabecalho(row);
                    continue;
                }

                DataFormatter formatter = new DataFormatter();

                try {
                    Integer ano = parseInt(formatter, row.getCell(0));
                    String uf = formatter.formatCellValue(row.getCell(1));
                    Integer idMuni = parseInt(formatter, row.getCell(2));
                    String muniNome = formatter.formatCellValue(row.getCell(3));
                    String idEscola = formatter.formatCellValue(row.getCell(4));
                    String rede = formatter.formatCellValue(row.getCell(5));
                    String tipoCategoria = formatter.formatCellValue(row.getCell(6));
                    String tipoLocalizacao = formatter.formatCellValue(row.getCell(7));

                    Integer banheiroPne = parseInt(formatter, row.getCell(8));
                    Integer dependenciaPne = parseInt(formatter, row.getCell(9));
                    Integer corrimao = parseInt(formatter, row.getCell(10));
                    Integer elevador = parseInt(formatter, row.getCell(11));
                    Integer pisoTatil = parseInt(formatter, row.getCell(12));
                    Integer vaoLivre = parseInt(formatter, row.getCell(13));
                    Integer rampas = parseInt(formatter, row.getCell(14));
                    Integer sinalSonoro = parseInt(formatter, row.getCell(15));
                    Integer sinalTatil = parseInt(formatter, row.getCell(16));
                    Integer sinalVisual = parseInt(formatter, row.getCell(17));
                    Integer acessibilidadeInex = parseInt(formatter, row.getCell(18));
                    Integer qtdSalaUtilAcessivel = parseInt(formatter, row.getCell(19));
                    Integer materialPedagoSurdo = parseInt(formatter, row.getCell(20));
                    Integer qtdMatriculaEducBasica = parseInt(formatter, row.getCell(21));
                    Integer qtdMatriculaEspecial = parseInt(formatter, row.getCell(22));
                    Integer qtdDocenteEducBasica = parseInt(formatter, row.getCell(23));
                    Integer qtdTurmaEspecial = parseInt(formatter, row.getCell(24));
                    Integer qtdTurmaEspecialComum = parseInt(formatter, row.getCell(25));
                    Integer qtdTurmaEspecialExclusiva = parseInt(formatter, row.getCell(26));

                    Escola escola = new Escola(
                            ano, uf, idMuni, muniNome, idEscola, rede, tipoCategoria, tipoLocalizacao,
                            banheiroPne, dependenciaPne, corrimao, elevador, pisoTatil, vaoLivre,
                            rampas, sinalSonoro, sinalTatil, sinalVisual, acessibilidadeInex,
                            qtdSalaUtilAcessivel, materialPedagoSurdo, qtdMatriculaEducBasica,
                            qtdMatriculaEspecial, qtdDocenteEducBasica, qtdTurmaEspecial,
                            qtdTurmaEspecialComum, qtdTurmaEspecialExclusiva
                    );

                    escolasExtraidas.add(escola);

                } catch (Exception e) {
                    System.out.println("Erro na linha " + row.getRowNum() + ": " + e.getMessage());
                }
            }

            System.out.println("Leitura finalizada");

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo do S3: " + e.getMessage());
        }

        return escolasExtraidas;
    }

    private Integer parseInt(DataFormatter formatter, Cell cell) {
        String valor = formatter.formatCellValue(cell);
        return valor.isEmpty() ? null : Integer.valueOf(valor);
    }

    private void printarCabecalho(Row row) {
        System.out.println("----- CABEÇALHO -----");
        for (int i = 0; i < row.getLastCellNum(); i++) {
            System.out.println("Coluna " + i + ": " + row.getCell(i));
        }
    }
}