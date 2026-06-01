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
               Olá! Tudo bem? 
               
               Passando para avisar que a base de dados foi atualizada com sucesso.

               Os dados mais recentes já estão no sistema para você consultar. Se houver qualquer informação que precise de revisão, a equipe responsável fará os ajustes necessários posteriormente.

               Um abraço,
               INCLUI🌻ME


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
                        ⚠️

                Não conseguimos concluir a atualização no momento. Sentimos muito pelo inconveniente!

                Tente novamente mais tarde ou aguarde a estabilização do sistema. Se o problema persistir, nossa equipe técnica já está acompanhando o caso.

                Um abraço,
                INCLUI🌻ME
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