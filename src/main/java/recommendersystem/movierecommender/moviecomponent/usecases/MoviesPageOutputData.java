package recommendersystem.movierecommender.moviecomponent.usecases;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoviesPageOutputData {
    private int pageSize;
    private int pageNumber;
    private int elementsNumber;
    private List<MovieOutputData> moviesOutputDataList;
    long totalElements;
    int totalPages;
}
