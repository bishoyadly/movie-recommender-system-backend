package recommendersystem.movierecommender.moviecomponent.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieInputBoundary;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MovieComponentTest {

    @Mock
    private MovieInputBoundary movieInputBoundary;

    private MovieComponent movieComponent;

    @BeforeEach
    void setUp() {
        movieComponent = new MovieComponent(movieInputBoundary);
    }

    @Test
    void getMovieById() {
        UUID movieId = UUID.randomUUID();
        movieComponent.getMovieById(movieId);
        verify(movieInputBoundary, times(1)).getMovieById(movieId);
    }

    @Test
    void getMostPopularMoviesList() {
        int pageSize = 5, pageNumber = 1;
        movieComponent.getMostPopularMoviesList(pageSize, pageNumber);
        verify(movieInputBoundary, times(1)).getMostPopularMoviesList(pageSize, pageNumber);
    }
}
