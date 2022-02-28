package recommendersystem.movierecommender.moviecomponent.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoviesPage {
    int recordsCount;
    int pageNumber;
    int pageSize;
    List<Movie> movieList;
}
