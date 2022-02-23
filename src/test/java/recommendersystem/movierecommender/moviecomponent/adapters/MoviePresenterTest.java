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

@ExtendWith(MockitoExtension.class)
class MoviePresenterTest {

    private MovieOutputBoundary movieOutputBoundary;

    private MoviePresenterMapper moviePresenterMapper;

    @BeforeEach
    void setUp() {
        moviePresenterMapper = new MoviePresenterMapperImpl();
        movieOutputBoundary = new MoviePresenter(moviePresenterMapper);
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
        ProblemDto problemDto = (ProblemDto) movieOutputBoundary.presentNotFoundErrorResponse(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);
        assertEquals(HttpStatus.NOT_FOUND.toString(), problemDto.getErrorMessage());
        assertEquals(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND, problemDto.getErrorDetail());
    }

    @Test
    void presentBadRequestErrorResponse() {
        ProblemDto problemDto = (ProblemDto) movieOutputBoundary.presentBadRequestErrorResponse(MovieUseCaseErrorMessages.INVALID_MOVIE_ID);
        assertEquals(HttpStatus.BAD_REQUEST.toString(), problemDto.getErrorMessage());
        assertEquals(MovieUseCaseErrorMessages.INVALID_MOVIE_ID, problemDto.getErrorDetail());
    }

}
