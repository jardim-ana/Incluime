package incluime.conectamais;


public class TesteSlack {

    public static void main(String[] args) {

        SlackService slackService = new SlackService();

        slackService.enviarMensagemAvaliacao(
                5,
                "A escola possui boa acessibilidade, mas poderia melhorar a entrada principal.",
                12
        );
    }
}