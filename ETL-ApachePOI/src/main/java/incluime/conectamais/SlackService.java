package incluime.conectamais;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class SlackService {
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    
private final String webhookUrl = System.getenv("SLACK_WEBHOOK_URL");

    public void enviarMensagemAvaliacao(Integer nota, String comentario, Integer idUsuario) {
        try {
            String texto = String.format(
                    """
                    Nova avaliação enviada no Conecta+

                    ID do aluno: %d
                    Nota: %d/5
                    Comentário: %s
                    """,
                    idUsuario,
                    nota,
                    comentario == null || comentario.isBlank() ? "Sem comentário" : comentario
            );

            SlackMensagemDto mensagem = new SlackMensagemDto(texto);

            String json = mapper.writeValueAsString(mensagem);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(webhookUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            System.out.println("Status Slack: " + response.statusCode());
            System.out.println("Resposta Slack: " + response.body());

        } catch (Exception e) {
            System.out.println("Erro ao enviar mensagem para o Slack: " + e.getMessage());
        }
    }
}
