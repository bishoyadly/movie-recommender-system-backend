package recommendersystem.movierecommender.web;

import org.mapstruct.Mapper;
import recommendersystem.movierecommender.model.MovieDto;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieResponse;

@Mapper(componentModel = "spring")
public interface MovieControllerMapper {

    MovieResponse movieDtoToMovieResponse(MovieDto movieDto);

    MovieDto movieResponseToMovieDto(MovieResponse response);
}
