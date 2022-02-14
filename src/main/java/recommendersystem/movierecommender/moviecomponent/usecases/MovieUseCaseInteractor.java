package recommendersystem.movierecommender.moviecomponent.usecases;

import recommendersystem.movierecommender.moviecomponent.entities.Movie;

import java.util.Objects;
import java.util.UUID;

public class MovieUseCaseInteractor implements MovieInputBoundary {

    private final MovieOutputBoundary movieOutputBoundary;
    private final MovieDataAccess movieDataAccess;
    private final MovieMapper movieMapper;

    public MovieUseCaseInteractor(MovieOutputBoundary movieOutputBoundary, MovieDataAccess movieDataAccess, MovieMapper movieMapper) {
        this.movieOutputBoundary = movieOutputBoundary;
        this.movieDataAccess = movieDataAccess;
        this.movieMapper = movieMapper;
    }

    @Override
    public Object getMovieById(UUID movieId) {
        if (notValidId(movieId)) return presentBadRequestErrorResponse();
        else return processGetMovieRequest(movieId);
    }

    private Object processGetMovieRequest(UUID movieId) {
        Movie movie = movieDataAccess.getMovieById(movieId);
        if (notValidId(movie.getId())) return presentNotFoundErrorResponse();
        else return presentMovieSuccessResponse(movie);
    }

    private boolean notValidId(UUID movieId) {
        return Objects.isNull(movieId);
    }

    //#region  Presenter Methods
    private Object presentMovieSuccessResponse(Movie movie) {
        MovieResponse response = movieMapper.movieToMovieResponse(movie);
        return movieOutputBoundary.presentMovieSuccessResponse(response);
    }

    private Object presentNotFoundErrorResponse() {
        return movieOutputBoundary.presentNotFoundErrorResponse(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);
    }

    private Object presentBadRequestErrorResponse() {
        return movieOutputBoundary.presentBadRequestErrorResponse(MovieUseCaseErrorMessages.INVALID_MOVIE_ID);
    }
    //#endregion
}
