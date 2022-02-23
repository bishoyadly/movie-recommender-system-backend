package recommendersystem.movierecommender.moviecomponent.adapters;

import org.mapstruct.Mapper;
import recommendersystem.movierecommender.model.MovieDto;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieOutputData;

@Mapper(componentModel = "spring")
interface MoviePresenterMapper {

    MovieOutputData movieDtoToMovieOutputData(MovieDto movieDto);

    MovieDto movieOutputDataToMovieDto(MovieOutputData response);
}
