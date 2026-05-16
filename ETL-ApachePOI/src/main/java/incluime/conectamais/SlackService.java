package incluime.conectamais;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class SlackService {

    private final String webhookUrl;
    private final HttpClient client;
    private final ObjectMapper mapper;

    public SlackService() {
        this.webhookUrl = System.getenv("SLACK_WEBHOOK_URL");

        if (this.webhookUrl == null || this.webhookUrl.isBlank()) {
            throw new IllegalStateException("Variável de ambiente SLACK_WEBHOOK_URL não configurada.");
        }

        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public void enviarMensagem(String texto) {
        try {
            SlackMensagemDto mensagemDto = new SlackMensagemDto(texto);

            String json = mapper.writeValueAsString(mensagemDto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(webhookUrl))
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                System.out.println("Erro ao enviar mensagem para o Slack. Status: " + response.statusCode());
                System.out.println("Resposta: " + response.body());
            }

        } catch (Exception e) {
            System.out.println("Erro ao enviar notificação para o Slack: " + e.getMessage());
        }
    }
}