package incluime.conectamais;

public class SlackMensagemDto {
    private String text;

    public SlackMensagemDto() {
    }

    public SlackMensagemDto(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
