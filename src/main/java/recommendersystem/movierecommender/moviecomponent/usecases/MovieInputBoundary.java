package recommendersystem.movierecommender.moviecomponent.usecases;

import java.util.UUID;

public interface MovieInputBoundary {
    Object getMovieById(UUID movieId);

    Object getMostPopularMoviesList(int pageSize, int pageNumber);
}
