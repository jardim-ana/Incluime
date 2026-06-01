package incluime.conectamais;

public class TesteEmail {
    
    public static void main(String[] args) {

        EmailService emailService = new EmailService();

        emailService.enviarEmail(
                "jardims.ana@gmail.com",
                "Atualização de dados concluída",
                """
        Olá! Tudo bem? 
               
        Passando para avisar que a base de dados foi atualizada com sucesso.

        Os dados mais recentes já estão no sistema para você consultar. Se houver qualquer informação que precise de revisão, a equipe responsável fará os ajustes necessários posteriormente.
        
        Um abraço,
        INCLUI🌻ME
                """
        );

        System.out.println("Teste de e-mail finalizado.");
    }
}
