package recommendersystem.movierecommender.moviecomponent.adapters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recommendersystem.movierecommender.moviecomponent.usecases.*;

@Configuration
public class MovieComponentConfig {

    @Bean(name = "movieInputBoundary")
    MovieInputBoundary movieInputBoundary(MoviePresenter moviePresenter, MoviePostgresDataAccess moviePostgresDataAccess) {
        MovieMapper movieMapper = new MovieMapperImpl();
        return new MovieUseCaseInteractor(moviePresenter, moviePostgresDataAccess, movieMapper);
    }

}
