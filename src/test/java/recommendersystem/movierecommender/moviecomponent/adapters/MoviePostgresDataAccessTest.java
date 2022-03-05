package recommendersystem.movierecommender.moviecomponent.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import recommendersystem.movierecommender.moviecomponent.entities.Movie;
import recommendersystem.movierecommender.moviecomponent.entities.MoviesPage;
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

    private void assertPageRequest(int pageSize, int pageNumber) {
        verify(movieRepository, times(1)).findAll(any(PageRequest.class));
        ArgumentCaptor<PageRequest> argumentCaptor = ArgumentCaptor.forClass(PageRequest.class);
        verify(movieRepository).findAll(argumentCaptor.capture());
        PageRequest actualPageRequest = argumentCaptor.getValue();
        assertEquals(pageSize, actualPageRequest.getPageSize());
        assertEquals(pageNumber - 1, actualPageRequest.getPageNumber());
        assertEquals("popularity: DESC", actualPageRequest.getSort().toString());
        assertEquals(Sort.Direction.DESC, actualPageRequest.getSort().getOrderFor("popularity").getDirection());
    }

    private void assertMoviesPage(MoviesPage expectedMoviesPage, MoviesPage moviesPage) {
        assertEquals(expectedMoviesPage.getPageSize(), moviesPage.getPageSize());
        assertEquals(expectedMoviesPage.getPageNumber(), moviesPage.getPageNumber());
        assertEquals(expectedMoviesPage.getTotalElements(), moviesPage.getTotalElements());
        assertEquals(expectedMoviesPage.getTotalPages(), moviesPage.getTotalPages());
        assertEquals(expectedMoviesPage.getMoviesList().size(), moviesPage.getMoviesList().size());
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

    @Test
    void getMostPopularMoviesList() {
        int pageSize = 5, pageNumber = 1;
        MoviesPage expectedMoviesPage = MovieDataUtils.buildMoviesPage(pageSize, pageNumber);
        Page<MovieRecord> recordsPage = MovieRecordFactory.buildRecordsPageFromMoviesPage(expectedMoviesPage);
        when(movieRepository.findAll(any(PageRequest.class))).thenReturn(recordsPage);
        MoviesPage returnedMoviesPage = movieDataAccess.getMostPopularMoviesList(pageSize, pageNumber);
        assertPageRequest(pageSize, pageNumber);
        assertMoviesPage(expectedMoviesPage, returnedMoviesPage);
    }
}
