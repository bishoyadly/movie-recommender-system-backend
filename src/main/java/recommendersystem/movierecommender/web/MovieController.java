package recommendersystem.movierecommender.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import recommendersystem.movierecommender.controller.MoviesApi;
import recommendersystem.movierecommender.model.MovieDto;
import recommendersystem.movierecommender.model.ProblemDto;
import recommendersystem.movierecommender.moviecomponent.adapters.MovieComponent;
import recommendersystem.movierecommender.moviecomponent.adapters.MovieException;
import recommendersystem.movierecommender.moviecomponent.usecases.MovieResponse;

import java.util.UUID;

@RestController
public class MovieController implements MoviesApi {

    private final MovieComponent movieComponent;
    private final MovieControllerMapper movieControllerMapper;

    @Autowired
    public MovieController(MovieComponent movieComponent, MovieControllerMapper movieControllerMapper) {
        this.movieComponent = movieComponent;
        this.movieControllerMapper = movieControllerMapper;
    }

    @Override
    public ResponseEntity<Object> getMovieById(UUID movieId) {
        try {
            return buildMovieResponse(movieId);
        } catch (MovieException e) {
            return buildMovieExceptionResponse(e);
        }
    }

    private ResponseEntity<Object> buildMovieResponse(UUID movieId) {
        MovieResponse response = (MovieResponse) movieComponent.getMovieById(movieId);
        MovieDto movieDto = movieControllerMapper.movieResponseToMovieDto(response);
        return ResponseEntity.ok(movieDto);
    }

    private ResponseEntity<Object> buildMovieExceptionResponse(MovieException e) {
        ProblemDto problemDto = new ProblemDto();
        problemDto.setErrorMessage(e.getHttpStatus().toString());
        problemDto.setErrorDetail(e.getErrorMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(problemDto);
    }
}
