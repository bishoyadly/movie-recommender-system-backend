package recommendersystem.movierecommender.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import recommendersystem.movierecommender.model.MovieDto;
import recommendersystem.movierecommender.model.ProblemDto;
import recommendersystem.movierecommender.moviecomponent.adapters.MovieComponent;
import recommendersystem.movierecommender.moviecomponent.adapters.MovieException;
import recommendersystem.movierecommender.moviecomponent.usecases.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest({MovieController.class, MovieComponent.class, MovieControllerMapper.class})
class MovieControllerTest {

    @MockBean
    private MovieComponent movieComponent;

    private MovieControllerMapper movieControllerMapper;

    private final MockMvc mockMvc;

    @Autowired
    public MovieControllerTest(MovieControllerMapper movieControllerMapper, MockMvc mockMvc) {
        this.movieControllerMapper = movieControllerMapper;
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
        MovieException exception = new MovieException(HttpStatus.NOT_FOUND, MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);
        when(movieComponent.getMovieById(movieId)).thenThrow(exception);
        String requestUrl = String.format("/movies/%s", movieId);
        ProblemDto expectedProblemDto = new ProblemDto();
        expectedProblemDto.setErrorMessage(HttpStatus.NOT_FOUND.toString());
        expectedProblemDto.setErrorDetail(MovieUseCaseErrorMessages.MOVIE_NOT_FOUND);

        MockHttpServletResponse response = performGetRequest(requestUrl);
        ProblemDto actualProblemDto = responseStringToProblemDto(response.getContentAsString());

        verify(movieComponent, Mockito.times(1)).getMovieById(movieId);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals(expectedProblemDto, actualProblemDto);
    }

    @Test
    @SneakyThrows
    void getMovieById_caseMovieExist() {
        UUID movieId = UUID.randomUUID();
        MovieResponse expectedResponse = MovieDataUtils.buildMovieResponse(movieId);
        when(movieComponent.getMovieById(movieId)).thenReturn(expectedResponse);
        String requestUrl = String.format("/movies/%s", movieId);

        MockHttpServletResponse response = performGetRequest(requestUrl);
        MovieDto movieDto = responseStringToMovieDto(response.getContentAsString());
        MovieResponse actualResponse = movieControllerMapper.movieDtoToMovieResponse(movieDto);
        verify(movieComponent, times(1)).getMovieById(movieId);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        MovieDataUtils.assertMovieResponseFields(expectedResponse, actualResponse);
    }
}
