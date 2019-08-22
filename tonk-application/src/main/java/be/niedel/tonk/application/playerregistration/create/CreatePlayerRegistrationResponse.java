package be.niedel.tonk.application.playerregistration.create;

import java.util.Objects;

public class CreatePlayerRegistrationResponse {

    private String message;

    public CreatePlayerRegistrationResponse() {
        this.message = "hello";
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePlayerRegistrationResponse that = (CreatePlayerRegistrationResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
