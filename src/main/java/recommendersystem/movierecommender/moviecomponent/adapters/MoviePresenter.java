package recommendersystem.movierecommender.moviecomponent.adapters;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import recommendersystem.movierecommender.model.ProblemDto;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieOutputBoundary;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieOutputData;

@Component
public class MoviePresenter implements MovieOutputBoundary {
    private final MoviePresenterMapper moviePresenterMapper;

    public MoviePresenter(MoviePresenterMapper moviePresenterMapper) {
        this.moviePresenterMapper = moviePresenterMapper;
    }

    @Override
    public Object presentMovieSuccessResponse(MovieOutputData movieOutputData) {
        return moviePresenterMapper.movieOutputDataToMovieDto(movieOutputData);
    }

    @Override
    public Object presentNotFoundErrorResponse(String errorDetailedMessage) {
        ProblemDto problemDto = new ProblemDto();
        problemDto.setErrorMessage(HttpStatus.NOT_FOUND.toString());
        problemDto.setErrorDetail(errorDetailedMessage);
        return problemDto;
    }

    @Override
    public Object presentBadRequestErrorResponse(String errorDetailedMessage) {
        ProblemDto problemDto = new ProblemDto();
        problemDto.setErrorMessage(HttpStatus.BAD_REQUEST.toString());
        problemDto.setErrorDetail(errorDetailedMessage);
        return problemDto;
    }
}
