package be.niedel.tonk.application.match.create;

import java.util.Objects;

public class PlayerDto {

    private final String id;
    private final String username;

    public PlayerDto() {
        id = null;
        username = null;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDto playerDto = (PlayerDto) o;
        return Objects.equals(id, playerDto.id) &&
                Objects.equals(username, playerDto.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
