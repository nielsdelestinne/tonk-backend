package be.niedel.tonk.adapter.webapi.springsocket;

import be.niedel.tonk.application.match.create.CreateMatchUseCase;
import be.niedel.tonk.domain.match.MatchRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchUseCaseBeanConfiguration {

    private final MatchRepository matchRepository;

    public MatchUseCaseBeanConfiguration(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Bean
    public CreateMatchUseCase createMatchUseCase() {
        return new CreateMatchUseCase(matchRepository);
    }

}
