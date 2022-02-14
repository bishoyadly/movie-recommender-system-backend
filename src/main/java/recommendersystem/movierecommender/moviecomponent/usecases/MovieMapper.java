package recommendersystem.movierecommender.moviecomponent.usecases;

import org.mapstruct.Mapper;
import recommendersystem.movierecommender.moviecomponent.entities.Movie;

@Mapper
public interface MovieMapper {
    MovieResponse movieToMovieResponse(Movie movie);
}
