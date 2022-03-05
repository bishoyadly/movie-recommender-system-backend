package recommendersystem.movierecommender.moviecomponent.adapters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import recommendersystem.movierecommender.moviecomponent.entities.Movie;
import recommendersystem.movierecommender.moviecomponent.entities.MoviesPage;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieDataAccess;

import java.util.Optional;
import java.util.UUID;

@Component
class MoviePostgresDataAccess implements MovieDataAccess {
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

    @Override
    public MoviesPage getMostPopularMoviesList(int pageSize, int pageNumber) {
        Page<MovieRecord> recordsPage = movieRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("popularity").descending()));
        MoviesPage moviesPage = new MoviesPage();
        moviesPage.setPageSize(pageSize);
        moviesPage.setPageNumber(pageNumber);
        moviesPage.setElementsNumber(recordsPage.getNumberOfElements());
        moviesPage.setMoviesList(movieRecordMapper.recordsListToMovieList(recordsPage.getContent()));
        moviesPage.setTotalElements(recordsPage.getTotalElements());
        moviesPage.setTotalPages(recordsPage.getTotalPages());
        return moviesPage;
    }
}
