package incluime.conectamais;

public class NotificacaoSlack {
    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Informe nota, comentário e idUsuario.");
            return;
        }

        Integer nota = Integer.valueOf(args[0]);
        String comentario = args[1];
        Integer idUsuario = Integer.valueOf(args[2]);

        SlackService slackService = new SlackService();

        slackService.enviarMensagemAvaliacao(
                nota,
                comentario,
                idUsuario
        );
    }
}
