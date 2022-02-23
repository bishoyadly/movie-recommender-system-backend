package recommendersystem.movierecommender.moviecomponent.usecases;

import org.mapstruct.Mapper;
import recommendersystem.movierecommender.moviecomponent.entities.Movie;

@Mapper
public interface MovieMapper {
    MovieOutputData movieToMovieResponse(Movie movie);
}
