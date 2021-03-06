package recommendersystem.movierecommender.moviecomponent.adapters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import recommendersystem.movierecommender.moviecomponent.entities.Genre;
import recommendersystem.movierecommender.moviecomponent.entities.Movie;
import recommendersystem.movierecommender.moviecomponent.entities.MoviesPage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

class MovieRecordFactory {

    public static Page<MovieRecord> buildRecordsPageFromMoviesPage(MoviesPage moviesPage) {
        List<MovieRecord> recordsList = new LinkedList<>();
        for (Movie movie : moviesPage.getMoviesList()) {
            MovieRecord record = buildMovieRecordFromMovie(movie);
            recordsList.add(record);
        }
        return new PageImpl<>(recordsList, PageRequest.of(moviesPage.getPageNumber(), moviesPage.getPageSize()), moviesPage.getTotalElements());
    }

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
        return List.of(actionGenre, comedyGenre);
    }

    public static MovieRecord buildMovieRecordFromMovie(Movie movie) {
        MovieRecord movieRecord = new MovieRecord();
        movieRecord.setId(movie.getId());
        movieRecord.setImdbId(movie.getImdbId());
        movieRecord.setTitle(movie.getTitle());
        movieRecord.setGenres(getGenresFromMovieGenresList(movie.getGenresList()));
        movieRecord.setMovieImgUrl(movie.getImageUrl());
        movieRecord.setVoteAverage(BigDecimal.valueOf(movie.getRating()));
        movieRecord.setVoteCount(movie.getRatingsCount());
        movieRecord.setOverview(movie.getOverview());
        movieRecord.setOriginalLanguage(movie.getOriginalLanguage());
        movieRecord.setReleaseDate(movie.getReleaseDate());
        movieRecord.setProductionCompanies(getProductionCompaniesAndCountriesFromMovie(movie.getProductionCompanies()));
        movieRecord.setProductionCountries(getProductionCompaniesAndCountriesFromMovie(movie.getProductionCountries()));
        return movieRecord;
    }

    private static List<JsonRecord> getGenresFromMovieGenresList(List<Genre> genreList) {
        List<JsonRecord> jsonRecordList = new ArrayList<>();
        for (Genre genre : genreList) {
            JsonRecord jsonRecord = new JsonRecord();
            jsonRecord.setName(genre.name());
            jsonRecordList.add(jsonRecord);
        }
        return jsonRecordList;
    }

    private static List<JsonRecord> getProductionCompaniesAndCountriesFromMovie(List<String> stringList) {
        List<JsonRecord> jsonRecordList = new ArrayList<>();
        for (String string : stringList) {
            JsonRecord jsonRecord = new JsonRecord();
            jsonRecord.setName(string);
            jsonRecordList.add(jsonRecord);
        }
        return jsonRecordList;
    }
}
