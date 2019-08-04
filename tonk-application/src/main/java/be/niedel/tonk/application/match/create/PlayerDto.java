package be.niedel.tonk.application.match.create;

public class PlayerDto {

    private final String id;
    private final String username;

    public PlayerDto(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
