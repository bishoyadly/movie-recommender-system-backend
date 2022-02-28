package recommendersystem.movierecommender.moviecomponent.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import recommendersystem.movierecommender.moviecomponent.entities.Movie;
import recommendersystem.movierecommender.moviecomponent.entities.MoviesPage;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieUseCaseInteractorTest {

    @Mock
    MovieOutputBoundary movieOutputBoundary;
    @Mock
    MovieDataAccess movieDataAccess;

    private MovieInputBoundary movieInputBoundary;

    void assertMovieResponse(MovieOutputData expectedResponse, MovieOutputData actualResponse) {
        ArgumentCaptor<MovieOutputData> argumentCaptor = ArgumentCaptor.forClass(MovieOutputData.class);
        verify(movieOutputBoundary).presentMovieSuccessResponse(argumentCaptor.capture());
        verify(movieOutputBoundary, times(1)).presentMovieSuccessResponse(any(MovieOutputData.class));
        MovieDataUtils.assertMovieOutputDataFields(expectedResponse, actualResponse);
        MovieDataUtils.assertMovieOutputDataFields(expectedResponse, argumentCaptor.getValue());
    }

    private void assertMoviesPageOutputData(MoviesPageOutputData expectedPage, MoviesPageOutputData actualPage) {
        ArgumentCaptor<MoviesPageOutputData> argumentCaptor = ArgumentCaptor.forClass(MoviesPageOutputData.class);
        verify(movieOutputBoundary, times(1)).presentMoviesPageSuccessResponse(any(MoviesPageOutputData.class));
        verify(movieOutputBoundary).presentMoviesPageSuccessResponse(argumentCaptor.capture());
        assertMoviesPageOutputDataFields(expectedPage, actualPage);
        assertMoviesPageOutputDataFields(expectedPage, argumentCaptor.getValue());
    }

    private void assertMoviesPageOutputDataFields(MoviesPageOutputData expectedPage, MoviesPageOutputData actualPage) {
        assertEquals(expectedPage.getRecordsCount(), actualPage.getRecordsCount());
        assertEquals(expectedPage.getPageNumber(), actualPage.getPageNumber());
        assertEquals(expectedPage.getPageSize(), actualPage.getPageSize());
        assertEquals(expectedPage.getMovieOutputDataList().size(), actualPage.getMovieOutputDataList().size());
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
        Movie expectedMovie = MovieDataUtils.buildMovie(movieId);
        MovieOutputData expectedMovieOutputData = MovieDataUtils.buildMovieOutputData(movieId);
        when(movieDataAccess.getMovieById(movieId)).thenReturn(expectedMovie);
        when(movieOutputBoundary.presentMovieSuccessResponse(any(MovieOutputData.class))).thenReturn(expectedMovieOutputData);

        MovieOutputData actualMovieOutputData = (MovieOutputData) movieInputBoundary.getMovieById(movieId);

        verify(movieDataAccess, times(1)).getMovieById(movieId);
        assertMovieResponse(expectedMovieOutputData, actualMovieOutputData);
    }

    @Test
    void getMostPopularMovies_caseInvalidPageSizeOrNumber() {
        movieInputBoundary.getMostPopularMoviesList(0, 1);
        verify(movieDataAccess, times(0)).getMostPopularMoviesList(anyInt(), anyInt());
        verify(movieOutputBoundary, times(1)).presentBadRequestErrorResponse(MovieUseCaseErrorMessages.INVALID_PAGE_SIZE);

        movieInputBoundary.getMostPopularMoviesList(1, 0);
        verify(movieDataAccess, times(0)).getMostPopularMoviesList(anyInt(), anyInt());
        verify(movieOutputBoundary, times(1)).presentBadRequestErrorResponse(MovieUseCaseErrorMessages.INVALID_PAGE_NUMBER);
    }

    @Test
    void getMostPopularMovies_caseNoMoviesExist() {
        int pageSize = 5, pageNumber = 1;
        MoviesPage moviesPage = new MoviesPage();
        moviesPage.setMovieList(new ArrayList<>());
        MoviesPageOutputData expectedPage = new MoviesPageOutputData();
        expectedPage.setMovieOutputDataList(new ArrayList<>());
        when(movieDataAccess.getMostPopularMoviesList(pageSize, pageNumber)).thenReturn(moviesPage);
        when(movieOutputBoundary.presentMoviesPageSuccessResponse(any(MoviesPageOutputData.class))).thenReturn(expectedPage);

        MoviesPageOutputData actualPage = (MoviesPageOutputData) movieInputBoundary.getMostPopularMoviesList(pageSize, pageNumber);

        verify(movieDataAccess, times(1)).getMostPopularMoviesList(pageSize, pageNumber);
        assertNotNull(actualPage);
        assertMoviesPageOutputData(expectedPage, actualPage);
    }


    @Test
    void getMostPopularMovies_caseMoviesExist() {
        int pageSize = 5, pageNumber = 1;
        MoviesPage moviesPage = MovieDataUtils.buildMoviesPage(pageSize, pageNumber);
        MoviesPageOutputData expectedPage = MovieDataUtils.buildMoviesPageOutputData(pageSize, pageNumber);
        when(movieDataAccess.getMostPopularMoviesList(pageSize, pageNumber)).thenReturn(moviesPage);
        when(movieOutputBoundary.presentMoviesPageSuccessResponse(any(MoviesPageOutputData.class))).thenReturn(expectedPage);

        MoviesPageOutputData actualPage = (MoviesPageOutputData) movieInputBoundary.getMostPopularMoviesList(pageSize, pageNumber);

        verify(movieDataAccess, times(1)).getMostPopularMoviesList(pageSize, pageNumber);
        assertNotNull(actualPage);
        assertMoviesPageOutputData(expectedPage, actualPage);
    }

}
