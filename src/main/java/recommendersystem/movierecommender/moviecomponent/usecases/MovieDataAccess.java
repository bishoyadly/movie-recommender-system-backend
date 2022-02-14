package recommendersystem.movierecommender.moviecomponent.usecases;

import recommendersystem.movierecommender.moviecomponent.entities.Movie;

import java.util.UUID;

public interface MovieDataAccess {
    Movie getMovieById(UUID movieId);
}
