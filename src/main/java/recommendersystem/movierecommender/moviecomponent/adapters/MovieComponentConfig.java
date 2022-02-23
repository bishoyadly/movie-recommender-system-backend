package recommendersystem.movierecommender.moviecomponent.adapters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recommendersystem.movierecommender.moviecomponent.usecases.*;

@Configuration
class MovieComponentConfig {

    @Bean(name = "movieInputBoundary")
    MovieInputBoundary movieInputBoundary(MovieApiPresenter movieApiPresenter, MoviePostgresDataAccess moviePostgresDataAccess) {
        MovieMapper movieMapper = new MovieMapperImpl();
        return new MovieUseCaseInteractor(movieApiPresenter, moviePostgresDataAccess, movieMapper);
    }

}
