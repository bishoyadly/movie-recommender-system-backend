package recommendersystem.movierecommender.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import recommendersystem.movierecommender.controller.MoviesApi;
import recommendersystem.movierecommender.model.MoviesPageDto;
import recommendersystem.movierecommender.moviecomponent.adapters.MovieApiPresenterException;
import recommendersystem.movierecommender.moviecomponent.adapters.MovieComponent;

import java.util.UUID;

@RestController
public class MovieController implements MoviesApi {

    private final MovieComponent movieComponent;

    @Autowired
    public MovieController(MovieComponent movieComponent) {
        this.movieComponent = movieComponent;
    }

    @Override
    public ResponseEntity<MoviesPageDto> getMostPopularMovies(Integer pageSize, Integer pageNumber) {
        return ResponseEntity.ok(movieComponent.getMostPopularMoviesList(pageSize, pageNumber));
    }

    @Override
    public ResponseEntity<Object> getMovieById(UUID movieId) {
        try {
            return ResponseEntity.ok(movieComponent.getMovieById(movieId));
        } catch (MovieApiPresenterException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getProblemDto());
        }
    }
}
