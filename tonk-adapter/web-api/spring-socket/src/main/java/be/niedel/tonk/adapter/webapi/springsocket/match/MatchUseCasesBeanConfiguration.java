package be.niedel.tonk.adapter.webapi.springsocket.match;

import be.niedel.tonk.application.match.create.CreateMatchUseCase;
import be.niedel.tonk.application.match.sendmatchactivity.SendMatchActivityUseCase;
import be.niedel.tonk.domain.match.MatchRepository;
import be.nielde.tonk.domain.gamesession.GameSessionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchUseCasesBeanConfiguration {

    private final MatchRepository matchRepository;
    private final GameSessionRepository gameSessionRepository;

    public MatchUseCasesBeanConfiguration(MatchRepository matchRepository,
                                          GameSessionRepository gameSessionRepository) {
        this.matchRepository = matchRepository;
        this.gameSessionRepository = gameSessionRepository;
    }

    @Bean
    public CreateMatchUseCase createMatchUseCase() {
        return new CreateMatchUseCase(matchRepository, gameSessionRepository);
    }

    @Bean
    public SendMatchActivityUseCase sendMatchActivityUseCase() {
        return new SendMatchActivityUseCase();
    }

}
