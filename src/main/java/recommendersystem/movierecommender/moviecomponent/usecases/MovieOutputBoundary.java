package recommendersystem.movierecommender.moviecomponent.usecases;

public interface MovieOutputBoundary {

    Object presentMoviesPageSuccessResponse(MoviesPageOutputData moviesPageOutputData);

    Object presentMovieSuccessResponse(MovieOutputData movieOutputData);

    Object presentNotFoundErrorResponse(String errorMessage);

    Object presentBadRequestErrorResponse(String errorMessage);
}
