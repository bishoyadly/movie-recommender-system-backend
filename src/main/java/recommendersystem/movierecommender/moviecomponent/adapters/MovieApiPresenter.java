package recommendersystem.movierecommender.moviecomponent.adapters;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieOutputBoundary;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieOutputData;

@Component
public class MovieApiPresenter implements MovieOutputBoundary {
    private final MoviePresenterMapper moviePresenterMapper;

    public MovieApiPresenter(MoviePresenterMapper moviePresenterMapper) {
        this.moviePresenterMapper = moviePresenterMapper;
    }

    @Override
    public Object presentMovieSuccessResponse(MovieOutputData movieOutputData) {
        return moviePresenterMapper.movieOutputDataToMovieDto(movieOutputData);
    }

    @Override
    public Object presentNotFoundErrorResponse(String errorDetailedMessage) {
        throw new MovieApiPresenterException(HttpStatus.NOT_FOUND, errorDetailedMessage);
    }

    @Override
    public Object presentBadRequestErrorResponse(String errorDetailedMessage) {
        throw new MovieApiPresenterException(HttpStatus.BAD_REQUEST, errorDetailedMessage);
    }
}