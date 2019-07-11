package be.niedel.tonk.adapter.webapi.springsocket.messages;

public class ChatMessage {

    private String from;
    private String text;

    public ChatMessage(String from, String text) {
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
