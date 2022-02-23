package recommendersystem.movierecommender.moviecomponent.usecases;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieDataUtils {
    public static MovieOutputData buildMovieOutputData(UUID movieId) {
        MovieOutputData outputData = new MovieOutputData();
        outputData.setId(movieId);
        outputData.setImdbID("imdb-id");
        outputData.setTitle("movie-title");
        outputData.setGenresList(List.of(GenreOutputData.ACTION, GenreOutputData.COMEDY));
        outputData.setImageUrl("image-url");
        outputData.setRating(Float.valueOf("4.7"));
        outputData.setRatingsCount(1);
        outputData.setOverview("overview");
        outputData.setOriginalLanguage("english");
        outputData.setReleaseDate("2020-10-29");
        outputData.setProductionCountries(List.of("USA", "China"));
        outputData.setProductionCompanies(List.of("Marvel", "Disney"));
        return outputData;
    }

    public static void assertMovieOutputDataFields(MovieOutputData expectedOutputData, MovieOutputData actualOutputData) {
        assertEquals(expectedOutputData.getId(), actualOutputData.getId());
        assertEquals(expectedOutputData.getImdbID(), actualOutputData.getImdbID());
        assertEquals(expectedOutputData.getTitle(), actualOutputData.getTitle());
        assertEquals(expectedOutputData.getGenresList(), actualOutputData.getGenresList());
        assertEquals(expectedOutputData.getImageUrl(), actualOutputData.getImageUrl());
        assertEquals(expectedOutputData.getRating(), actualOutputData.getRating());
        assertEquals(expectedOutputData.getRatingsCount(), actualOutputData.getRatingsCount());
        assertEquals(expectedOutputData.getOverview(), actualOutputData.getOverview());
        assertEquals(expectedOutputData.getOriginalLanguage(), actualOutputData.getOriginalLanguage());
        assertEquals(expectedOutputData.getReleaseDate(), actualOutputData.getReleaseDate());
        assertEquals(expectedOutputData.getProductionCountries(), actualOutputData.getProductionCountries());
        assertEquals(expectedOutputData.getProductionCompanies(), actualOutputData.getProductionCompanies());
    }
}
