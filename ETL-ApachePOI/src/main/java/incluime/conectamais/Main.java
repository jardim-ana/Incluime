package incluime.conectamais;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        EmailService emailService = new EmailService();

        String[] nomeArquivo = {
                "excel/escolas-query.xlsx",
                "excel/nomes-escola.xlsx"
        };

        BaseETL logger = new LeitorExcel();

        try {

            System.out.println("Início da MAIN");

            Conexao conexaoBanco = new Conexao();

            try (Connection conexao = conexaoBanco.getConexao()) {

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

                LeitorExcel leitor = new LeitorExcel();

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

                System.out.println("Processamento finalizado");

                emailService.enviarEmailParaUsuariosComNotificacaoAtiva(
                        conexao,
                        "Atualização de dados concluída",
                        """
                        A base de dados foi atualizada com sucesso.

                        As informações mais recentes já estão disponíveis no sistema para consulta.

                        Caso algum dado precise de revisão, ele poderá ser ajustado posteriormente pela equipe responsável.
                        """
                );
            }

        } catch (Exception e) {

            try {

                Conexao conexaoBanco = new Conexao();

                try (Connection conexao = conexaoBanco.getConexao()) {

                    logger.log(
                            conexao,
                            "Erro na MAIN: " + e.getMessage(),
                            "ERROR"
                    );

                    emailService.enviarEmailParaUsuariosComNotificacaoAtiva(
                            conexao,
                            "Falha na atualização de dados",
                            """
                            Não foi possível concluir a atualização da base de dados.

                            A equipe responsável poderá verificar o ocorrido.

                            Tente novamente mais tarde ou aguarde uma nova atualização do sistema.
                            """
                    );
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }
}