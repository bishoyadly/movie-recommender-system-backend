package recommendersystem.movierecommender.controllers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import recommendersystem.movierecommender.entities.MoviesMetadatum;
import recommendersystem.movierecommender.entities.MoviesMetadatumRepo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

@Service
@Slf4j
public class MovieService {
    private MoviesMetadatumRepo metadatumRepo;
    private ThreadPoolTaskExecutor taskExecutor;

    public MovieService(MoviesMetadatumRepo metadatumRepo, ThreadPoolTaskExecutor taskExecutor) {
        this.metadatumRepo = metadatumRepo;
        this.taskExecutor = taskExecutor;
        taskExecutor.setCorePoolSize(200);
        taskExecutor.setMaxPoolSize(300);
    }

    @SneakyThrows
    @Async
    public void getAllMoviesImages() {
        List<MoviesMetadatum> moviesMetadatumList = new LinkedList<>(metadatumRepo.findAllRemainingMovies());
        List<Future> futureList = new LinkedList<>();
        for (int i = 0; i < moviesMetadatumList.size(); i++) {
            int index = i;
            Thread.sleep(300);
            Future<String> future = taskExecutor.submit(() -> saveMovieUrl(moviesMetadatumList.get(index)));
            futureList.add(future);
        }
//        for (Future future : futureList) {
//            log.info("movie {} finished", future.get());
//        }
    }

    private String saveMovieUrl(MoviesMetadatum metadatum) {
        try {
            String movieImageUrl = getMovieImageUrl(metadatum.getImdbId());
            metadatum.setMovieImgUrl(movieImageUrl);
            metadatumRepo.save(metadatum);
            log.info("movie {} image url saved", metadatum.getId());
            return String.valueOf(metadatum.getId());
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        return "";
    }

    @SneakyThrows
    String getMovieImageUrl(String movieId) {
        String movieUrl = String.format("https://www.imdb.com/title/%s", movieId);
        Document doc = Jsoup.connect(movieUrl).get();
        String imageUrl = doc.select("[data-testid=\"hero-media__poster\"] img").get(0).attributes().get("src");
        log.info("image url {}", imageUrl);
        return imageUrl;
    }
}
