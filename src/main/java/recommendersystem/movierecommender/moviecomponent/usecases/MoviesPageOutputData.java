package recommendersystem.movierecommender.moviecomponent.usecases;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoviesPageOutputData {
    private int recordsCount;
    private int pageSize;
    private int pageNumber;
    private List<MovieOutputData> movieOutputDataList;
}
