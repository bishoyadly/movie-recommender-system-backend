package recommendersystem.movierecommender.moviedatapreparation;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviedatapreparation")
@Slf4j
@EnableAsync
public class MovieDataPreparationController {

    private final MovieDataPreparationService movieDataPreparationService;

    @Autowired
    public MovieDataPreparationController(MovieDataPreparationService movieDataPreparationService) {
        this.movieDataPreparationService = movieDataPreparationService;
    }

    @PostMapping
    @SneakyThrows
    void prepareMoviesData(@RequestBody TemplateRequest request) {
        movieDataPreparationService.updateMovies(request.getMovieId());
    }
}
