package recommendersystem.movierecommender.moviecomponent.usecases;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import recommendersystem.movierecommender.moviecomponent.entities.Genre;
import recommendersystem.movierecommender.moviecomponent.entities.Movie;
import recommendersystem.movierecommender.moviecomponent.entities.MoviesPage;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieDataUtils {
    public static void assertMovieOutputDataFields(MovieOutputData expectedOutputData, MovieOutputData actualOutputData) {
        assertEquals(expectedOutputData.getId(), actualOutputData.getId());
        assertEquals(expectedOutputData.getImdbId(), actualOutputData.getImdbId());
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

    public static MovieOutputData buildMovieOutputData(UUID movieId) {
        MovieOutputData outputData = new MovieOutputData();
        outputData.setId(movieId);
        outputData.setImdbId("imdb-id");
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

    public static MoviesPage buildMoviesPage(int pageSize, int pageNumber) {
        Movie movie1 = buildMovie(UUID.randomUUID());
        Movie movie2 = buildMovie(UUID.randomUUID());
        List<Movie> moviesList = List.of(movie1, movie2);
        MoviesPage moviesPage = new MoviesPage();
        moviesPage.setPageSize(pageSize);
        moviesPage.setPageNumber(pageNumber);
        moviesPage.setElementsNumber(moviesList.size());
        moviesPage.setTotalPages(100 / pageSize);
        moviesPage.setTotalElements(100);
        moviesPage.setMoviesList(moviesList);
        return moviesPage;
    }

    public static MoviesPageOutputData buildMoviesPageOutputDataFromMoviesPage(MoviesPage moviesPage) {
        MoviesPageOutputData moviesPageOutputData = new MoviesPageOutputData();
        moviesPageOutputData.setPageSize(moviesPage.getPageSize());
        moviesPageOutputData.setPageNumber(moviesPage.getPageNumber());
        moviesPageOutputData.setElementsNumber(moviesPage.getElementsNumber());
        moviesPageOutputData.setTotalElements(moviesPage.getTotalElements());
        moviesPageOutputData.setTotalPages(moviesPage.getTotalPages());
        List<MovieOutputData> moviesOutputDataList = new LinkedList<>();
        for (Movie movie : moviesPage.getMoviesList()) {
            MovieOutputData movieOutputData = buildMovieOutputDataFromMovie(movie);
            moviesOutputDataList.add(movieOutputData);
        }
        moviesPageOutputData.setMoviesOutputDataList(moviesOutputDataList);
        return moviesPageOutputData;
    }

    public static MovieOutputData buildMovieOutputDataFromMovie(Movie movie) {
        MovieOutputData outputData = new MovieOutputData();
        outputData.setId(movie.getId());
        outputData.setImdbId(movie.getImdbId());
        outputData.setTitle(movie.getTitle());
        outputData.setGenresList(movie.getGenresList().stream().map(genre -> GenreOutputData.valueOf(genre.name())).collect(Collectors.toList()));
        outputData.setImageUrl(movie.getImageUrl());
        outputData.setRating(movie.getRating());
        outputData.setRatingsCount(movie.getRatingsCount());
        outputData.setOverview(movie.getOverview());
        outputData.setOriginalLanguage(movie.getOriginalLanguage());
        outputData.setReleaseDate(movie.getReleaseDate());
        outputData.setProductionCountries(movie.getProductionCountries());
        outputData.setProductionCompanies(movie.getProductionCompanies());
        return outputData;
    }

    public static Movie buildMovie(UUID movieId) {
        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setImdbId("imdb-id");
        movie.setTitle("movie-title");
        movie.setGenresList(List.of(Genre.ACTION, Genre.COMEDY));
        movie.setImageUrl("image-url");
        movie.setRating(Float.valueOf("4.7"));
        movie.setRatingsCount(1);
        movie.setOverview("overview");
        movie.setOriginalLanguage("english");
        movie.setReleaseDate("2020-10-29");
        movie.setProductionCountries(List.of("USA", "China"));
        movie.setProductionCompanies(List.of("Marvel", "Disney"));
        return movie;
    }
}
