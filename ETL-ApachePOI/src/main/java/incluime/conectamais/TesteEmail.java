package incluime.conectamais;

public class TesteEmail {
    
    public static void main(String[] args) {

        EmailService emailService = new EmailService();

        emailService.enviarEmail(
                "felisouza30@gmail.com",
                "Teste",
                """

                A base de dados foi atualizada com sucesso.

                As informações mais recentes já estão disponíveis no sistema para consulta.

                Caso algum dado precise de revisão, ele poderá ser ajustado posteriormente pela equipe responsável.
                """
        );

        System.out.println("Teste de e-mail finalizado.");
    }
}
