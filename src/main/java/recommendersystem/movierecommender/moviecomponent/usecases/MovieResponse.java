package recommendersystem.movierecommender.moviecomponent.usecases;

import lombok.Getter;
import lombok.Setter;
import recommendersystem.movierecommender.moviecomponent.entities.Genres;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MovieResponse {
    private UUID id;
    private String imdbID;
    private String title;
    List<Genres> genresList;
    String imageUrl;
    Float rating;
    Integer ratingsCount;
    String overview;
    String originalLanguage;
    String releaseDate;
    List<String> productionCountries;
    List<String> productionCompanies;
}
