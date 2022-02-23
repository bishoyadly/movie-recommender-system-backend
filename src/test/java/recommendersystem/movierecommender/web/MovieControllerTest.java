package recommendersystem.movierecommender.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import recommendersystem.movierecommender.model.GenreDto;
import recommendersystem.movierecommender.model.MovieDto;
import recommendersystem.movierecommender.model.ProblemDto;
import recommendersystem.movierecommender.moviecomponent.adapters.MovieApiPresenterException;
import recommendersystem.movierecommender.moviecomponent.adapters.MovieComponent;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieUseCaseErrorMessages;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest({MovieController.class, MovieComponent.class})
class MovieControllerTest {

    @MockBean
    private MovieComponent movieComponent;

    private final MockMvc mockMvc;

    @Autowired
    public MovieControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @SneakyThrows
    private MockHttpServletResponse performGetRequest(String requestUrl) {
        return mockMvc.perform(
                        MockMvcRequestBuilders.get(requestUrl).contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
    }

    @SneakyThrows
    public static String objectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @SneakyThrows
    public static ProblemDto responseStringToProblemDto(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, ProblemDto.class);
    }

    @SneakyThrows
    public static MovieDto responseStringToMovieDto(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, MovieDto.class);
    }

    private MovieDto buildMovieDto(UUID movieId) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movieId);
        movieDto.setImdbId("imdb-id");
        movieDto.setTitle("movie-title");
        movieDto.setGenresList(List.of(GenreDto.ACTION, GenreDto.COMEDY));
        movieDto.setImageUrl("image-url");
        movieDto.setRating(Float.valueOf("4.7"));
        movieDto.setRatingsCount(1);
        movieDto.setOverview("overview");
        movieDto.setOriginalLanguage("english");
        movieDto.setReleaseDate("2020-10-29");
        movieDto.setProductionCountries(List.of("USA", "China"));
        movieDto.setProductionCompanies(List.of("Marvel", "Disney"));
        return movieDto;
    }

    private void assertMovieDtoFields(MovieDto expectedMovieDto, MovieDto actualMovieDto) {
        assertEquals(expectedMovieDto.getId(), actualMovieDto.getId());
        assertEquals(expectedMovieDto.getImdbId(), actualMovieDto.getImdbId());
        assertEquals(expectedMovieDto.getTitle(), actualMovieDto.getTitle());
        assertEquals(expectedMovieDto.getGenresList(), actualMovieDto.getGenresList());
        assertEquals(expectedMovieDto.getImageUrl(), actualMovieDto.getImageUrl());
        assertEquals(expectedMovieDto.getRating(), actualMovieDto.getRating());
        assertEquals(expectedMovieDto.getRatingsCount(), actualMovieDto.getRatingsCount());
        assertEquals(expectedMovieDto.getOverview(), actualMovieDto.getOverview());
        assertEquals(expectedMovieDto.getOriginalLanguage(), actualMovieDto.getOriginalLanguage());
        assertEquals(expectedMovieDto.getReleaseDate(), actualMovieDto.getReleaseDate());
        assertEquals(expectedMovieDto.getProductionCountries(), actualMovieDto.getProductionCountries());
        assertEquals(expectedMovieDto.getProductionCompanies(), actualMovieDto.getProductionCompanies());
    }

    @Test
    void getMovieById_caseNullId() {
        String requestUrl = "/movies/";
        MockHttpServletResponse response = performGetRequest(requestUrl);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void getMovieById_caseInvalid() {
        String movieId = "invalidId";
        String requestUrl = String.format("/movies/%s", movieId);
        MockHttpServletResponse response = performGetRequest(requestUrl);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @SneakyThrows
    void getMovieById_caseMovieNotExists() {
        UUID movieId = UUID.randomUUID();
        ProblemDto expectedProblemDto = new ProblemDto();
        expectedProblemDto.setErrorMessageTitle(HttpStatus.NOT_FOUND.toString());
        expectedProblemDto.setErrorMessageDetail(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);
        MovieApiPresenterException exception = new MovieApiPresenterException(HttpStatus.NOT_FOUND, MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);
        when(movieComponent.getMovieById(movieId)).thenThrow(exception);
        String requestUrl = String.format("/movies/%s", movieId);

        MockHttpServletResponse response = performGetRequest(requestUrl);
        ProblemDto actualProblemDto = responseStringToProblemDto(response.getContentAsString());

        verify(movieComponent, times(1)).getMovieById(movieId);
        assertEquals(expectedProblemDto.getErrorMessageTitle(), actualProblemDto.getErrorMessageTitle());
        assertEquals(expectedProblemDto.getErrorMessageDetail(), actualProblemDto.getErrorMessageDetail());
    }

    @Test
    @SneakyThrows
    void getMovieById_caseMovieExist() {
        UUID movieId = UUID.randomUUID();
        MovieDto expectedMovieDto = buildMovieDto(movieId);
        when(movieComponent.getMovieById(movieId)).thenReturn(expectedMovieDto);
        String requestUrl = String.format("/movies/%s", movieId);

        MockHttpServletResponse response = performGetRequest(requestUrl);
        MovieDto actualMovieDto = responseStringToMovieDto(response.getContentAsString());
        verify(movieComponent, times(1)).getMovieById(movieId);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertMovieDtoFields(expectedMovieDto, actualMovieDto);
    }
}
