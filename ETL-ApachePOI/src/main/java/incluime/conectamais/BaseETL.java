package incluime.conectamais;

import org.apache.poi.ss.usermodel.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class BaseETL {

    protected Cell getCell(Row row, int index) {

        return row.getCell(
                index,
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK
        );
    }

    protected Integer parseInt(
            DataFormatter formatter,
            Cell cell
    ) {

        try {

            String valor =
                    formatter
                            .formatCellValue(cell)
                            .trim();

            if (valor.isEmpty()) {
                return null;
            }

            return (int) Double.parseDouble(valor);

        } catch (Exception e) {

            return null;
        }
    }

    public void log(
            Connection conexao,
            String mensagem,
            String nivel
    ) {

        String sql =
                "INSERT INTO logss (acao, tipo) VALUES (?, ?)";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, mensagem);
            stmt.setString(2, nivel);

            stmt.executeUpdate();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao salvar log"
            );
        }
    }
}