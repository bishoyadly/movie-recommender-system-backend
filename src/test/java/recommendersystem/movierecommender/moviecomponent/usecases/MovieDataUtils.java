package recommendersystem.movierecommender.moviecomponent.usecases;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieDataUtils {
    public static MovieResponse buildMovieResponse(UUID movieId) {
        MovieResponse response = new MovieResponse();
        response.setId(movieId);
        response.setImdbID("imdb-id");
        response.setTitle("movie-title");
        response.setGenresList(List.of(GenreResponse.ACTION, GenreResponse.COMEDY));
        response.setImageUrl("image-url");
        response.setRating(Float.valueOf("4.7"));
        response.setRatingsCount(1);
        response.setOverview("overview");
        response.setOriginalLanguage("english");
        response.setReleaseDate("2020-10-29");
        response.setProductionCountries(List.of("USA", "China"));
        response.setProductionCompanies(List.of("Marvel", "Disney"));
        return response;
    }

    public static void assertMovieResponseFields(MovieResponse expectedResponse, MovieResponse actualResponse) {
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getImdbID(), actualResponse.getImdbID());
        assertEquals(expectedResponse.getTitle(), actualResponse.getTitle());
        assertEquals(expectedResponse.getGenresList(), actualResponse.getGenresList());
        assertEquals(expectedResponse.getImageUrl(), actualResponse.getImageUrl());
        assertEquals(expectedResponse.getRating(), actualResponse.getRating());
        assertEquals(expectedResponse.getRatingsCount(), actualResponse.getRatingsCount());
        assertEquals(expectedResponse.getOverview(), actualResponse.getOverview());
        assertEquals(expectedResponse.getOriginalLanguage(), actualResponse.getOriginalLanguage());
        assertEquals(expectedResponse.getReleaseDate(), actualResponse.getReleaseDate());
        assertEquals(expectedResponse.getProductionCountries(), actualResponse.getProductionCountries());
        assertEquals(expectedResponse.getProductionCompanies(), actualResponse.getProductionCompanies());
    }
}
