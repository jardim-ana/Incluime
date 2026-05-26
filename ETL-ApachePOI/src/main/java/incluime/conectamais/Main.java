package incluime.conectamais;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        SlackService slackService =
                new SlackService();

        String[] nomeArquivo = {
                "excel/escolas-query.xlsx",
                "excel/nomes-escola.xlsx"
        };

        BaseETL logger =
                new LeitorExcel();

        try {

            System.out.println(
                    "Início da MAIN"
            );

            Conexao conexaoBanco =
                    new Conexao();

            try (
                    Connection conexao =
                            conexaoBanco.getConexao()
            ) {

                logger.log(
                        conexao,
                        "MAIN iniciada",
                        "INFO"
                );

                logger.log(
                        conexao,
                        "Conexão com banco estabelecida",
                        "INFO"
                );

                logger.log(
                        conexao,
                        "Arquivos recebidos: "
                                + nomeArquivo[0]
                                + " | "
                                + nomeArquivo[1],
                        "INFO"
                );

                LeitorExcel leitor =
                        new LeitorExcel();

                logger.log(
                        conexao,
                        "Iniciando processamento ETL",
                        "INFO"
                );

                leitor.extrairEscolas(
                        nomeArquivo,
                        conexao
                );

                logger.log(
                        conexao,
                        "ETL finalizada com sucesso",
                        "INFO"
                );

                System.out.println(
                        "Processamento finalizado"
                );

                slackService.enviarMensagem("""
                        ✅ ETL executado com sucesso

                        Arquivos:
                        - %s
                        - %s

                        Processo:
                        Leitura e junção de bases com Apache POI

                        Banco de destino:
                        MySQL

                        Status:
                        Processamento finalizado
                        """.formatted(
                        nomeArquivo[0],
                        nomeArquivo[1]
                ));
            }

        } catch (Exception e) {

            try {

                Conexao conexaoBanco =
                        new Conexao();

                try (
                        Connection conexao =
                                conexaoBanco.getConexao()
                ) {

                    logger.log(
                            conexao,
                            "Erro na MAIN: "
                                    + e.getMessage(),
                            "ERROR"
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            slackService.enviarMensagem("""
                    ❌ Falha na execução do ETL

                    Arquivos:
                    - %s
                    - %s

                    Processo:
                    Leitura e junção de bases com Apache POI

                    Banco de destino:
                    MySQL

                    Erro:
                    %s

                    Status:
                    Falha no processamento
                    """.formatted(
                    nomeArquivo[0],
                    nomeArquivo[1],
                    e.getMessage()
            ));

            e.printStackTrace();
        }
    }
}