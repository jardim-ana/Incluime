package incluime.conectamais;

import org.springframework.jdbc.core.JdbcTemplate;

public class Main {
    public static void main(String[] args) {

        SlackService slackService = new SlackService();

        String nomeArquivo = "excel/escolas-query.xlsx";

        try {
            System.out.println("Início da MAIN");

            Conexao conexao = new Conexao();
            JdbcTemplate template = new JdbcTemplate(conexao.getConexao());

            LeitorExcel leitor = new LeitorExcel();

            leitor.extrairEscolas(nomeArquivo, template);

            System.out.println("Processamento finalizado");

            slackService.enviarMensagem("""
                    ✅ ETL executado com sucesso

                    Arquivo: %s
                    Processo: Leitura de planilha com Apache POI
                    Banco de destino: MySQL
                    Status: Processamento finalizado
                    """.formatted(nomeArquivo));

        } catch (Exception e) {

            slackService.enviarMensagem("""
                    ❌ Falha na execução do ETL

                    Arquivo: %s
                    Processo: Leitura de planilha com Apache POI
                    Banco de destino: MySQL
                    Erro: %s
                    Status: Falha no processamento
                    """.formatted(nomeArquivo, e.getMessage()));

            e.printStackTrace();
        }
    }
}