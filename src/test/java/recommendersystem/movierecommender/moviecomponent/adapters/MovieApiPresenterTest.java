package recommendersystem.movierecommender.moviecomponent.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import recommendersystem.movierecommender.model.MovieDto;
import recommendersystem.movierecommender.model.ProblemDto;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieDataUtils;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieOutputBoundary;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieOutputData;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieUseCaseErrorMessages;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MovieApiPresenterTest {

    private MovieOutputBoundary movieOutputBoundary;

    private MoviePresenterMapper moviePresenterMapper;

    private void assertMovieNotFoundProblemDto(ProblemDto expectedDto) {
        ProblemDto actualDto = new ProblemDto();
        try {
            movieOutputBoundary.presentNotFoundErrorResponse(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);
        } catch (MovieApiPresenterException e) {
            actualDto = e.getProblemDto();
        }
        assertEquals(expectedDto.getErrorMessage(), actualDto.getErrorMessage());
        assertEquals(expectedDto.getErrorDetail(), actualDto.getErrorDetail());
    }

    private void assertMovieBadRequestProblemDto(ProblemDto expectedDto) {
        ProblemDto actualDto = new ProblemDto();
        try {
            movieOutputBoundary.presentBadRequestErrorResponse(expectedDto.getErrorDetail());
        } catch (MovieApiPresenterException e) {
            actualDto = e.getProblemDto();
        }
        assertEquals(expectedDto.getErrorMessage(), actualDto.getErrorMessage());
        assertEquals(expectedDto.getErrorDetail(), actualDto.getErrorDetail());
    }

    @BeforeEach
    void setUp() {
        moviePresenterMapper = new MoviePresenterMapperImpl();
        movieOutputBoundary = new MovieApiPresenter(moviePresenterMapper);
    }

    @Test
    void presentMovieSuccessResponse() {
        UUID movieId = UUID.randomUUID();
        MovieOutputData expectedOutputData = MovieDataUtils.buildMovieOutputData(movieId);
        MovieDto movieDto = (MovieDto) movieOutputBoundary.presentMovieSuccessResponse(expectedOutputData);
        MovieOutputData actualOutputData = moviePresenterMapper.movieDtoToMovieOutputData(movieDto);
        MovieDataUtils.assertMovieOutputDataFields(expectedOutputData, actualOutputData);
    }

    @Test
    void presentNotFoundErrorResponse() {
        assertThrows(MovieApiPresenterException.class, () -> movieOutputBoundary.presentNotFoundErrorResponse(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND));
        ProblemDto expectedProblemDto = new ProblemDto();
        expectedProblemDto.setErrorMessage(HttpStatus.NOT_FOUND.toString());
        expectedProblemDto.setErrorDetail(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);
        assertMovieNotFoundProblemDto(expectedProblemDto);
    }

    @Test
    void presentBadRequestErrorResponse() {
        assertThrows(MovieApiPresenterException.class, () -> movieOutputBoundary.presentBadRequestErrorResponse(MovieUseCaseErrorMessages.INVALID_MOVIE_ID));
        ProblemDto expectedProblemDto = new ProblemDto();
        expectedProblemDto.setErrorMessage(HttpStatus.BAD_REQUEST.toString());
        expectedProblemDto.setErrorDetail(MovieUseCaseErrorMessages.INVALID_MOVIE_ID);
        assertMovieBadRequestProblemDto(expectedProblemDto);
    }

}
