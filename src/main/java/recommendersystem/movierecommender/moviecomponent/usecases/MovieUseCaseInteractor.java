package recommendersystem.movierecommender.moviecomponent.usecases;

import recommendersystem.movierecommender.moviecomponent.entities.Movie;
import recommendersystem.movierecommender.moviecomponent.entities.MoviesPage;

import java.util.*;

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
        if (isValidId(movieId))
            return processGetMovieRequest(movieId);
        else
            return presentBadRequestErrorResponse(MovieUseCaseErrorMessages.INVALID_MOVIE_ID);
    }

    @Override
    public Object getMostPopularMoviesList(int pageSize, int pageNumber) {
        if (invalidPageSize(pageSize))
            return presentBadRequestErrorResponse(MovieUseCaseErrorMessages.INVALID_PAGE_SIZE);
        if (invalidPageNumber(pageNumber))
            return presentBadRequestErrorResponse(MovieUseCaseErrorMessages.INVALID_PAGE_NUMBER);
        MoviesPage moviesPage = movieDataAccess.getMostPopularMoviesList(pageSize, pageNumber);
        return presentMoviesPageOutputDataSuccessResponse(moviesPage);
    }

    private Object processGetMovieRequest(UUID movieId) {
        Movie movie = movieDataAccess.getMovieById(movieId);
        if (isValidId(movie.getId()))
            return presentMovieSuccessResponse(movie);
        else
            return presentNotFoundErrorResponse();
    }


    private boolean invalidPageSize(int pageSize) {
        return pageSize < 5;
    }

    private boolean invalidPageNumber(int pageNumber) {
        return pageNumber < 1;
    }

    private boolean isValidId(UUID movieId) {
        return Objects.nonNull(movieId);
    }

    //#region  Presenter Methods
    private Object presentMovieSuccessResponse(Movie movie) {
        MovieOutputData movieOutputData = movieMapper.movieToMovieOutputData(movie);
        return movieOutputBoundary.presentMovieSuccessResponse(movieOutputData);
    }

    private Object presentMoviesPageOutputDataSuccessResponse(MoviesPage moviesPage) {
        MoviesPageOutputData moviesPageOutputData = movieMapper.moviesPageToMoviesPageOutputData(moviesPage);
        return movieOutputBoundary.presentMoviesPageSuccessResponse(moviesPageOutputData);
    }

    private Object presentNotFoundErrorResponse() {
        return movieOutputBoundary.presentNotFoundErrorResponse(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);
    }

    private Object presentBadRequestErrorResponse(String errorMessage) {
        return movieOutputBoundary.presentBadRequestErrorResponse(errorMessage);
    }
    //#endregion
}
