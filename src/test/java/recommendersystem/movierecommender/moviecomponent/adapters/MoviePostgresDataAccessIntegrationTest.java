package recommendersystem.movierecommender.moviecomponent.adapters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
class MoviePostgresDataAccessIntegrationTest {

    MovieRepository movieRepository;

    @Autowired
    MoviePostgresDataAccessIntegrationTest(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Test
    void getMovieById() {
        UUID movieId = UUID.fromString("fe5efc54-8dbc-11ec-8b28-4f26f96302cf");
        boolean existed = movieRepository.existsById(movieId);
        MovieRecord savedRecord = movieRepository.findById(movieId).get();
        assertTrue(existed);
        assertNotNull(savedRecord);
    }
}
