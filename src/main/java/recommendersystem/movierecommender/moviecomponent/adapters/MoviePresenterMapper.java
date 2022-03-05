package recommendersystem.movierecommender.moviecomponent.adapters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import recommendersystem.movierecommender.model.MovieDto;
import recommendersystem.movierecommender.model.MoviesPageDto;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieOutputData;
import recommendersystem.movierecommender.moviecomponent.usecases.MoviesPageOutputData;

@Mapper(componentModel = "spring")
interface MoviePresenterMapper {

    MovieOutputData movieDtoToMovieOutputData(MovieDto movieDto);

    MovieDto movieOutputDataToMovieDto(MovieOutputData response);

    @Mapping(target = "content", source = "moviesOutputDataList")
    MoviesPageDto pageOutputDataToPageDto(MoviesPageOutputData pageOutputData);
}
