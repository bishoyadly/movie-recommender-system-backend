package recommendersystem.movierecommender.moviecomponent.adapters;

import org.springframework.stereotype.Component;
import recommendersystem.movierecommender.moviecomponent.entities.Movie;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieDataAccess;

import java.util.Optional;
import java.util.UUID;

@Component
public class MoviePostgresDataAccess implements MovieDataAccess {
    private final MovieRepository movieRepository;
    private final MovieRecordMapper movieRecordMapper;

    public MoviePostgresDataAccess(MovieRepository movieRepository, MovieRecordMapper movieRecordMapper) {
        this.movieRepository = movieRepository;
        this.movieRecordMapper = movieRecordMapper;
    }

    @Override
    public Movie getMovieById(UUID movieId) {
        Optional<MovieRecord> optional = movieRepository.findById(movieId);
        if (optional.isPresent())
            return movieRecordMapper.recordToMovie(optional.get());
        else
            return new Movie();
    }
}
