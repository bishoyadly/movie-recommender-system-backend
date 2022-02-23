package recommendersystem.movierecommender.moviecomponent.usecases;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MovieOutputData {
    private UUID id;
    private String imdbId;
    private String title;
    List<GenreOutputData> genresList;
    String imageUrl;
    Float rating;
    Integer ratingsCount;
    String overview;
    String originalLanguage;
    String releaseDate;
    List<String> productionCountries;
    List<String> productionCompanies;
}
