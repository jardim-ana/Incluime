package incluime.conectamais;

import org.springframework.jdbc.core.JdbcTemplate;

public class Main {
    public static void main(String[] args) {

        System.out.println("Início da MAIN");

        Conexao conexao = new Conexao();
        JdbcTemplate template = new JdbcTemplate(conexao.getConexao());

        LeitorExcel leitor = new LeitorExcel();

        leitor.extrairEscolas("excel/escolas-query.xlsx", template);

        System.out.println("Processamento finalizado");
    }
}