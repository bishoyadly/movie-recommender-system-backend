package recommendersystem.movierecommender.moviecomponent.usecases;

public interface MovieOutputBoundary {

    Object presentMovieSuccessResponse(MovieResponse movieResponse);

    Object presentNotFoundErrorResponse(String errorMessage);

    Object presentBadRequestErrorResponse(String errorMessage);
}
