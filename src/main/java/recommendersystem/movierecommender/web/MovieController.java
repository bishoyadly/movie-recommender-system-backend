package recommendersystem.movierecommender.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recommendersystem.movierecommender.controller.MoviesApi;
import recommendersystem.movierecommender.moviecomponent.adapters.MovieApiPresenterException;
import recommendersystem.movierecommender.moviecomponent.adapters.MovieComponent;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class MovieController implements MoviesApi {

    private final MovieComponent movieComponent;

    @Autowired
    public MovieController(MovieComponent movieComponent) {
        this.movieComponent = movieComponent;
    }

    @Override
    public ResponseEntity<Object> getMostPopularMovies(Integer pageSize, Integer pageNumber) {
        try {
            return ResponseEntity.ok(movieComponent.getMostPopularMoviesList(pageSize, pageNumber));
        } catch (MovieApiPresenterException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getProblemDto());
        }
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
