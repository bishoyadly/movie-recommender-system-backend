package recommendersystem.movierecommender.moviecomponent.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import recommendersystem.movierecommender.moviecomponent.entities.Genres;
import recommendersystem.movierecommender.moviecomponent.entities.Movie;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieUseCaseInteractorTest {

    @Mock
    MovieOutputBoundary movieOutputBoundary;
    @Mock
    MovieDataAccess movieDataAccess;

    private MovieInputBoundary movieInputBoundary;

    Movie buildMovie(UUID movieId) {
        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setImdbID("imdb-id");
        movie.setTitle("movie-title");
        movie.setGenresList(List.of(Genres.ACTION, Genres.COMEDY));
        movie.setImageUrl("image-url");
        movie.setRating(Float.valueOf("4.7"));
        movie.setRatingsCount(1);
        movie.setOverview("overview");
        movie.setOriginalLanguage("english");
        movie.setReleaseDate("2020-10-29");
        movie.setProductionCountries(List.of("USA", "China"));
        movie.setProductionCompanies(List.of("Marvel", "Disney"));
        return movie;
    }

    MovieResponse buildMovieResponse(UUID movieId) {
        MovieResponse response = new MovieResponse();
        response.setId(movieId);
        response.setImdbID("imdb-id");
        response.setTitle("movie-title");
        response.setGenresList(List.of(Genres.ACTION, Genres.COMEDY));
        response.setImageUrl("image-url");
        response.setRating(Float.valueOf("4.7"));
        response.setRatingsCount(1);
        response.setOverview("overview");
        response.setOriginalLanguage("english");
        response.setReleaseDate("2020-10-29");
        response.setProductionCountries(List.of("USA", "China"));
        response.setProductionCompanies(List.of("Marvel", "Disney"));
        return response;
    }

    void assertMovieResponse(MovieResponse expectedResponse, MovieResponse actualResponse) {
        ArgumentCaptor<MovieResponse> argumentCaptor = ArgumentCaptor.forClass(MovieResponse.class);
        verify(movieOutputBoundary).presentMovieSuccessResponse(argumentCaptor.capture());
        verify(movieOutputBoundary, times(1)).presentMovieSuccessResponse(any(MovieResponse.class));
        assertMovieResponseFields(expectedResponse, actualResponse);
        assertMovieResponseFields(expectedResponse, argumentCaptor.getValue());
    }

    void assertMovieResponseFields(MovieResponse expectedResponse, MovieResponse actualResponse) {
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getImdbID(), actualResponse.getImdbID());
        assertEquals(expectedResponse.getTitle(), actualResponse.getTitle());
        assertEquals(expectedResponse.getGenresList(), actualResponse.getGenresList());
        assertEquals(expectedResponse.getImageUrl(), actualResponse.getImageUrl());
        assertEquals(expectedResponse.getRating(), actualResponse.getRating());
        assertEquals(expectedResponse.getRatingsCount(), actualResponse.getRatingsCount());
        assertEquals(expectedResponse.getOverview(), actualResponse.getOverview());
        assertEquals(expectedResponse.getOriginalLanguage(), actualResponse.getOriginalLanguage());
        assertEquals(expectedResponse.getReleaseDate(), actualResponse.getReleaseDate());
        assertEquals(expectedResponse.getProductionCountries(), actualResponse.getProductionCountries());
        assertEquals(expectedResponse.getProductionCompanies(), actualResponse.getProductionCompanies());
    }

    @BeforeEach
    void setUp() {
        MovieMapper movieMapper = new MovieMapperImpl();
        movieInputBoundary = new MovieUseCaseInteractor(movieOutputBoundary, movieDataAccess, movieMapper);
    }

    @Test
    void getMovieById_caseNullId() {
        MovieUseCaseError expectedError = new MovieUseCaseError(MovieUseCaseErrorMessages.INVALID_MOVIE_ID);
        when(movieOutputBoundary.presentBadRequestErrorResponse(MovieUseCaseErrorMessages.INVALID_MOVIE_ID)).thenReturn(expectedError);

        MovieUseCaseError actualUseCaseError = (MovieUseCaseError) movieInputBoundary.getMovieById(null);

        verify(movieDataAccess, times(0)).getMovieById(any(UUID.class));
        verify(movieOutputBoundary).presentBadRequestErrorResponse(MovieUseCaseErrorMessages.INVALID_MOVIE_ID);
        assertEquals(expectedError.getErrorMessage(), actualUseCaseError.getErrorMessage());
    }

    @Test
    void getMovieById_caseNotFound() {
        UUID movieId = UUID.randomUUID();
        MovieUseCaseError expectedError = new MovieUseCaseError(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);
        when(movieDataAccess.getMovieById(movieId)).thenReturn(new Movie());
        when(movieOutputBoundary.presentNotFoundErrorResponse(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND)).thenReturn(expectedError);

        MovieUseCaseError actualUseCaseError = (MovieUseCaseError) movieInputBoundary.getMovieById(movieId);

        verify(movieDataAccess, times(1)).getMovieById(any(UUID.class));
        verify(movieOutputBoundary).presentNotFoundErrorResponse(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);
        assertEquals(expectedError.getErrorMessage(), actualUseCaseError.getErrorMessage());
    }

    @Test
    void getMovieById_caseMovieExisted() {
        UUID movieId = UUID.randomUUID();
        Movie expectedMovie = buildMovie(movieId);
        MovieResponse expectedMovieResponse = buildMovieResponse(movieId);
        when(movieDataAccess.getMovieById(movieId)).thenReturn(expectedMovie);
        when(movieOutputBoundary.presentMovieSuccessResponse(any(MovieResponse.class))).thenReturn(expectedMovieResponse);

        MovieResponse actualMovieResponse = (MovieResponse) movieInputBoundary.getMovieById(movieId);

        verify(movieDataAccess, times(1)).getMovieById(movieId);
        assertMovieResponse(expectedMovieResponse, actualMovieResponse);
    }
}
