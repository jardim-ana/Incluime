package incluime.conectamais;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class LeitorExcel {
    public List<Escola> extrairEscolas(String nomeArquivo) {
        List<Escola> escolasExtraidas = new ArrayList<>();

        try (
                InputStream arquivo = new FileInputStream(nomeArquivo);
                Workbook workbook = new XSSFWorkbook(arquivo)
        ) {

            System.out.printf("Iniciando leitura do arquivo %s%n", nomeArquivo);

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    printarCabecalho(row);
                    continue;
                }

                System.out.println("Lendo linha " + row.getRowNum());

                DataFormatter formatter = new DataFormatter();

                Integer ano = Integer.valueOf(formatter.formatCellValue(row.getCell(0)));
                String uf = formatter.formatCellValue(row.getCell(1));
                Integer idMuni = Integer.valueOf(formatter.formatCellValue(row.getCell(2)));
                String muniNome = formatter.formatCellValue(row.getCell(3));
                String idEscola = formatter.formatCellValue(row.getCell(4));
                String rede = formatter.formatCellValue(row.getCell(5));
                String tipoCategoria = formatter.formatCellValue(row.getCell(6));
                String tipoLocalizacao = formatter.formatCellValue(row.getCell(7));
                Integer banheiroPne = Integer.valueOf(formatter.formatCellValue(row.getCell(8)));
                Integer dependenciaPne = Integer.valueOf(formatter.formatCellValue(row.getCell(9)));
                Integer corrimao = Integer.valueOf(formatter.formatCellValue(row.getCell(10)));
                Integer elevador = Integer.valueOf(formatter.formatCellValue(row.getCell(11)));
                Integer pisoTatil = Integer.valueOf(formatter.formatCellValue(row.getCell(12)));
                Integer vaoLivre = Integer.valueOf(formatter.formatCellValue(row.getCell(13)));
                Integer rampas = Integer.valueOf(formatter.formatCellValue(row.getCell(14)));
                Integer sinalSonoro = Integer.valueOf(formatter.formatCellValue(row.getCell(15)));
                Integer sinalTatil = Integer.valueOf(formatter.formatCellValue(row.getCell(16)));
                Integer sinalVisual = Integer.valueOf(formatter.formatCellValue(row.getCell(17)));
                Integer acessibilidadeInex = Integer.valueOf(formatter.formatCellValue(row.getCell(18)));
                Integer qtdSalaUtilAcessivel = Integer.valueOf(formatter.formatCellValue(row.getCell(19)));
                Integer materialPedagoSurdo = Integer.valueOf(formatter.formatCellValue(row.getCell(20)));
                Integer qtdMatriculaEducBasica = Integer.valueOf(formatter.formatCellValue(row.getCell(21)));
                Integer qtdMatriculaEspecial = Integer.valueOf(formatter.formatCellValue(row.getCell(22)));
                Integer qtdDocenteEducBasica = Integer.valueOf(formatter.formatCellValue(row.getCell(23)));
                Integer qtdTurmaEspecial = Integer.valueOf(formatter.formatCellValue(row.getCell(24)));
                Integer qtdTurmaEspecialComum = Integer.valueOf(formatter.formatCellValue(row.getCell(25)));
                Integer qtdTurmaEspecialExclusiva = Integer.valueOf(formatter.formatCellValue(row.getCell(26)));

                Escola escola = new Escola(ano, uf, idMuni, muniNome, idEscola, rede, tipoCategoria, tipoLocalizacao, banheiroPne, dependenciaPne, corrimao, elevador, pisoTatil, vaoLivre
                , rampas, sinalSonoro, sinalTatil, sinalVisual, acessibilidadeInex, qtdSalaUtilAcessivel, materialPedagoSurdo, qtdMatriculaEducBasica
                , qtdMatriculaEspecial, qtdDocenteEducBasica, qtdTurmaEspecial, qtdTurmaEspecialComum, qtdTurmaEspecialExclusiva);

                escolasExtraidas.add(escola);
            }

            printarLinhas();
            System.out.println("Leitura do arquivo finalizada");
            printarLinhas();

            return escolasExtraidas;
        } catch (IOException e) {
            return escolasExtraidas;
        }
    }

    private void printarCabecalho(Row row) {
        printarLinhas();
        System.out.println("Lendo cabeçalho");
        for (int i = 0; i < 26; i++) {
            String coluna = row.getCell(i).getStringCellValue();
            System.out.println("Coluna " + i + ": " + coluna);
        }
        printarLinhas();
    }

    private void printarLinhas() {
        System.out.println("-".repeat(20));
    }
}

