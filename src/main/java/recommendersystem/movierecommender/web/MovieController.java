package recommendersystem.movierecommender.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import recommendersystem.movierecommender.controller.MoviesApi;
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
    public ResponseEntity<Object> getMovieById(UUID movieId) {
        return ResponseEntity.ok(movieComponent.getMovieById(movieId));
    }
}
