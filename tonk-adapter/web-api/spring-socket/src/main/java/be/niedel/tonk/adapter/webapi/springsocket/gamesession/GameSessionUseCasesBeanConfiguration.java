package be.niedel.tonk.adapter.webapi.springsocket.gamesession;

import be.niedel.tonk.application.gamesession.create.CreateGameSessionUseCase;
import be.niedel.tonk.application.gamesession.getall.GetAllGameSessionsUseCase;
import be.niedel.tonk.application.gamesession.remove.RemoveGameSessionUseCase;
import be.nielde.tonk.domain.gamesession.GameSessionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameSessionUseCasesBeanConfiguration {

    private final GameSessionRepository gameSessionRepository;

    public GameSessionUseCasesBeanConfiguration(GameSessionRepository gameSessionRepository) {
        this.gameSessionRepository = gameSessionRepository;
    }

    @Bean
    public CreateGameSessionUseCase createGameSessionUseCase() {
        return new CreateGameSessionUseCase(gameSessionRepository);
    }

    @Bean
    public RemoveGameSessionUseCase removeGameSessionUseCase() {
        return new RemoveGameSessionUseCase(gameSessionRepository);
    }

    @Bean
    public GetAllGameSessionsUseCase getAllGameSessionsUseCase() {
        return new GetAllGameSessionsUseCase(gameSessionRepository);
    }

}
