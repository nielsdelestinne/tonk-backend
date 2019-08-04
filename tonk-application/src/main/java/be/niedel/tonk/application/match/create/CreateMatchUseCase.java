package be.niedel.tonk.application.match.create;

import be.niedel.tonk.domain.match.MatchRepository;

public class CreateMatchUseCase {

    private final MatchRepository repository;
    private final CreateMatchMapper mapper;

    public CreateMatchUseCase(MatchRepository repository) {
        this.repository = repository;
        this.mapper = new CreateMatchMapper();
    }

    public MatchCreateResponse process(MatchCreateRequest request) {
        return mapper.toResponse(
                repository.save(mapper.toDomain(request)));
    }
}
