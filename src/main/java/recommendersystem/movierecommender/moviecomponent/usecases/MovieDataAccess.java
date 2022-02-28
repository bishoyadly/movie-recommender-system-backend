package recommendersystem.movierecommender.moviecomponent.usecases;

import recommendersystem.movierecommender.moviecomponent.entities.Movie;
import recommendersystem.movierecommender.moviecomponent.entities.MoviesPage;

import java.util.UUID;

public interface MovieDataAccess {
    Movie getMovieById(UUID movieId);

    MoviesPage getMostPopularMoviesList(int pageSize, int pageNumber);
}
