package recommendersystem.movierecommender.moviecomponent.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import recommendersystem.movierecommender.moviecomponent.entities.Genre;
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
        movie.setGenresList(List.of(Genre.ACTION, Genre.COMEDY));
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

    void assertMovieResponse(MovieOutputData expectedResponse, MovieOutputData actualResponse) {
        ArgumentCaptor<MovieOutputData> argumentCaptor = ArgumentCaptor.forClass(MovieOutputData.class);
        verify(movieOutputBoundary).presentMovieSuccessResponse(argumentCaptor.capture());
        verify(movieOutputBoundary, times(1)).presentMovieSuccessResponse(any(MovieOutputData.class));
        MovieDataUtils.assertMovieOutputDataFields(expectedResponse, actualResponse);
        MovieDataUtils.assertMovieOutputDataFields(expectedResponse, argumentCaptor.getValue());
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
        MovieOutputData expectedMovieOutputData = MovieDataUtils.buildMovieOutputData(movieId);
        when(movieDataAccess.getMovieById(movieId)).thenReturn(expectedMovie);
        when(movieOutputBoundary.presentMovieSuccessResponse(any(MovieOutputData.class))).thenReturn(expectedMovieOutputData);

        MovieOutputData actualMovieOutputData = (MovieOutputData) movieInputBoundary.getMovieById(movieId);

        verify(movieDataAccess, times(1)).getMovieById(movieId);
        assertMovieResponse(expectedMovieOutputData, actualMovieOutputData);
    }
}
