package recommendersystem.movierecommender.moviecomponent.adapters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    void getMostPopularMovies() {
        PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("popularity").descending());
        Page<MovieRecord> movieRecordPage = movieRepository.findAll(pageRequest);
        assertNotNull(movieRecordPage);
        assertEquals(pageRequest.getPageNumber(), movieRecordPage.getNumber());
        assertEquals(pageRequest.getPageSize(), movieRecordPage.getSize());
        assertTrue(movieRecordPage.getTotalElements() > 100);
    }
}
