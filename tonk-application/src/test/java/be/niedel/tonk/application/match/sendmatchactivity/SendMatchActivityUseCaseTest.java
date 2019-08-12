package be.niedel.tonk.application.match.sendmatchactivity;

import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

class SendMatchActivityUseCaseTest {

    @Test
    void process_givenMatchActivity_thenReturnThatMatchActivity() {
        // GIVEN
        SendMatchActivityUseCase sendMatchActivityUseCase = new SendMatchActivityUseCase();
        MatchActivityDto sentMatchActivityDto = new MatchActivityDto(randomUUID().toString(), "Jim", 15.5, 20.52);

        // WHEN
        MatchActivityDto returnedMatchActivityDto = sendMatchActivityUseCase
                .process(sentMatchActivityDto);

        // THEN
        assertThat(returnedMatchActivityDto).isEqualToComparingFieldByField(sentMatchActivityDto);

    }
}
