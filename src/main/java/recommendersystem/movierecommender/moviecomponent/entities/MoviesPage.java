package recommendersystem.movierecommender.moviecomponent.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoviesPage {
    int pageSize;
    int pageNumber;
    int elementsNumber;
    List<Movie> moviesList;
    long totalElements;
    int totalPages;
}
