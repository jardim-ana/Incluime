package incluime.conectamais;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailService {

    private final String remetente = System.getenv("remete");
private final String senhaApp = System.getenv("senhaAPP");

    public void enviarEmail(String destinatario, String assunto, String corpo) {
        try {
            Properties props = new Properties();

            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(
                    props,
                    new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    remetente,
                                    senhaApp
                            );
                        }
                    }
            );

            Message message = new MimeMessage(session);

            message.setFrom(
                    new InternetAddress(remetente)
            );

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            );

            message.setSubject(assunto);
            message.setText(corpo);

            Transport.send(message);

            System.out.println(
                    "E-mail enviado com sucesso para: " + destinatario
            );

        } catch (Exception erro) {
            System.out.println(
                    "Erro ao enviar e-mail para: " + destinatario
            );

            erro.printStackTrace();
        }
    }

    public List<String> buscarEmailsComNotificacaoAtiva(Connection conexao) {
        List<String> emails = new ArrayList<>();

        String sql = """
                SELECT email
                FROM usuario
                WHERE notificacao_email = 1
                AND email IS NOT NULL
                AND email <> ''
                """;

        try (
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {
                emails.add(
                        rs.getString("email")
                );
            }

        } catch (Exception erro) {
            System.out.println(
                    "Erro ao buscar e-mails com notificação ativa."
            );

            erro.printStackTrace();
        }

        return emails;
    }

    public void enviarEmailParaUsuariosComNotificacaoAtiva(
            Connection conexao,
            String assunto,
            String corpo
    ) {
        List<String> emails =
                buscarEmailsComNotificacaoAtiva(conexao);

        if (emails.isEmpty()) {
            System.out.println(
                    "Nenhum usuário com notificação por e-mail ativa."
            );

            return;
        }

        for (String email : emails) {
            enviarEmail(
                    email,
                    assunto,
                    corpo
            );
        }
    }
}