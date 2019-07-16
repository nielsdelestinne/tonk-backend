package be.niedel.tonk.adapter.webapi.springsocket.messages;

public class ChatMessageDto {

    private String from;
    private String text;

    public ChatMessageDto(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public String getText() {
        return text;
    }
}
