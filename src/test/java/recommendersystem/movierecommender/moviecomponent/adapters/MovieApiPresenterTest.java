package recommendersystem.movierecommender.moviecomponent.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import recommendersystem.movierecommender.model.MovieDto;
import recommendersystem.movierecommender.model.MoviesPageDto;
import recommendersystem.movierecommender.model.ProblemDto;
import recommendersystem.movierecommender.moviecomponent.usecases.*;

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
        assertEquals(expectedDto.getErrorMessageTitle(), actualDto.getErrorMessageTitle());
        assertEquals(expectedDto.getErrorMessageDetail(), actualDto.getErrorMessageDetail());
    }

    private void assertMovieBadRequestProblemDto(ProblemDto expectedDto) {
        ProblemDto actualDto = new ProblemDto();
        try {
            movieOutputBoundary.presentBadRequestErrorResponse(expectedDto.getErrorMessageDetail());
        } catch (MovieApiPresenterException e) {
            actualDto = e.getProblemDto();
        }
        assertEquals(expectedDto.getErrorMessageTitle(), actualDto.getErrorMessageTitle());
        assertEquals(expectedDto.getErrorMessageDetail(), actualDto.getErrorMessageDetail());
    }

    @BeforeEach
    void setUp() {
        moviePresenterMapper = new MoviePresenterMapperImpl();
        movieOutputBoundary = new MovieApiPresenter(moviePresenterMapper);
    }

    @Test
    void presentMoviesPageSuccessResponse() {
        MoviesPageOutputData moviesPageOutputData = MovieDataUtils.buildMoviesPageOutputData(5, 1);
        MoviesPageDto moviesPageDto = (MoviesPageDto) movieOutputBoundary.presentMoviesPageSuccessResponse(moviesPageOutputData);
        assertEquals(moviesPageOutputData.getPageSize(), moviesPageDto.getPageSize());
        assertEquals(moviesPageOutputData.getPageNumber(), moviesPageDto.getPageNumber());
        assertEquals(moviesPageOutputData.getElementsNumber(), moviesPageDto.getElementsNumber());
        assertEquals(moviesPageOutputData.getTotalElements(), moviesPageDto.getTotalElements());
        assertEquals(moviesPageOutputData.getTotalPages(), moviesPageDto.getTotalPages());
        assertEquals(moviesPageOutputData.getMoviesOutputDataList().size(), moviesPageDto.getContent().size());
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
        expectedProblemDto.setErrorMessageTitle(HttpStatus.NOT_FOUND.toString());
        expectedProblemDto.setErrorMessageDetail(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);
        assertMovieNotFoundProblemDto(expectedProblemDto);
    }

    @Test
    void presentBadRequestErrorResponse() {
        assertThrows(MovieApiPresenterException.class, () -> movieOutputBoundary.presentBadRequestErrorResponse(MovieUseCaseErrorMessages.INVALID_MOVIE_ID));
        ProblemDto expectedProblemDto = new ProblemDto();
        expectedProblemDto.setErrorMessageTitle(HttpStatus.BAD_REQUEST.toString());
        expectedProblemDto.setErrorMessageDetail(MovieUseCaseErrorMessages.INVALID_MOVIE_ID);
        assertMovieBadRequestProblemDto(expectedProblemDto);
    }

}
