package recommendersystem.movierecommender.moviecomponent.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import recommendersystem.movierecommender.moviecomponent.entities.Movie;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieDataAccess;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieDataUtils;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoviePostgresDataAccessTest {

    @Mock
    MovieRepository movieRepository;
    private MovieDataAccess movieDataAccess;
    private MovieRecordMapper movieRecordMapper;

    void assertMovieFields(Movie expectedMovie, Movie actualMovie) {
        assertEquals(expectedMovie.getId(), actualMovie.getId());
        assertEquals(expectedMovie.getImdbId(), actualMovie.getImdbId());
        assertEquals(expectedMovie.getTitle(), actualMovie.getTitle());
        assertEquals(expectedMovie.getGenresList(), actualMovie.getGenresList());
        assertEquals(expectedMovie.getImageUrl(), actualMovie.getImageUrl());
        assertEquals(expectedMovie.getRating(), actualMovie.getRating());
        assertEquals(expectedMovie.getRatingsCount(), actualMovie.getRatingsCount());
        assertEquals(expectedMovie.getOverview(), actualMovie.getOverview());
        assertEquals(expectedMovie.getOriginalLanguage(), actualMovie.getOriginalLanguage());
        assertEquals(expectedMovie.getReleaseDate(), actualMovie.getReleaseDate());
        assertEquals(expectedMovie.getProductionCompanies(), actualMovie.getProductionCompanies());
        assertEquals(expectedMovie.getProductionCountries(), actualMovie.getProductionCountries());
    }

    @BeforeEach
    void setUp() {
        movieRecordMapper = new MovieRecordMapperImpl();
        movieDataAccess = new MoviePostgresDataAccess(movieRepository, movieRecordMapper);
    }

    @Test
    void getMovieById() {
        UUID movieId = UUID.randomUUID();
        Movie expectedMovie = MovieDataUtils.buildMovie(movieId);
        MovieRecord record = MovieRecordFactory.buildMovieRecordFromMovie(expectedMovie);
        when(movieRepository.findById(movieId)).thenReturn(Optional.of(record));
        Movie returnedMovie = movieDataAccess.getMovieById(movieId);
        verify(movieRepository, times(1)).findById(movieId);
        assertMovieFields(expectedMovie, returnedMovie);
    }
}
