package recommendersystem.movierecommender.moviecomponent.adapters;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

class MovieRecordFactory {

    public static MovieRecord buildMovieRecord(UUID movieId) {
        MovieRecord movieRecord = new MovieRecord();
        movieRecord.setId(movieId);
        movieRecord.setImdbId("imdb-id");
        movieRecord.setTitle("movie-title");
        movieRecord.setGenres(buildGenreList());
        movieRecord.setMovieImgUrl("image-url");
        movieRecord.setVoteAverage(BigDecimal.valueOf(8.7));
        movieRecord.setVoteCount(10);
        movieRecord.setOverview("overview");
        movieRecord.setOriginalLanguage("english");
        movieRecord.setReleaseDate("10-10-2010");
        movieRecord.setProductionCompanies(buildProductionCompanies());
        movieRecord.setProductionCountries(buildProductionCountries());
        return movieRecord;
    }

    static List<JsonRecord> buildProductionCompanies() {
        JsonRecord company1 = new JsonRecord();
        company1.setId("1");
        company1.setName("company1");
        JsonRecord company2 = new JsonRecord();
        company2.setId("2");
        company2.setName("company2");
        return List.of(company1, company2);
    }

    static List<JsonRecord> buildProductionCountries() {
        JsonRecord country1 = new JsonRecord();
        country1.setId("1");
        country1.setName("country1");
        JsonRecord country2 = new JsonRecord();
        country2.setId("2");
        country2.setName("country2");
        return List.of(country1, country2);
    }

    static List<JsonRecord> buildGenreList() {
        JsonRecord comedyGenre = new JsonRecord();
        comedyGenre.setId("1");
        comedyGenre.setName("comedy");
        JsonRecord actionGenre = new JsonRecord();
        actionGenre.setId("2");
        actionGenre.setName("action");
        return List.of(comedyGenre, actionGenre);
    }
}
