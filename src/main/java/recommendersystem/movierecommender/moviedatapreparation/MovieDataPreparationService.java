package recommendersystem.movierecommender.moviedatapreparation;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class MovieDataPreparationService {
    private MoviesMetadatumRepo metadatumRepo;
    private ThreadPoolTaskExecutor taskExecutor;

    public MovieDataPreparationService(MoviesMetadatumRepo metadatumRepo, ThreadPoolTaskExecutor taskExecutor) {
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

    @SneakyThrows
    @Transactional
    void updateMovies(UUID movieId) {
        AtomicInteger counter = new AtomicInteger(0);
        metadatumRepo.findAll().forEach(moviesMetadatum -> {
            taskExecutor.submit(() -> {
                moviesMetadatum.setProductionCountries(replaceJsonIfNotValid(moviesMetadatum.getProductionCountries()));
//                moviesMetadatum.setProductionCompanies(replaceJsonIfNotValid(moviesMetadatum.getProductionCompanies()));
//                moviesMetadatum.setGenres(correctJson(moviesMetadatum.getGenres()));
//                moviesMetadatum.setSpokenLanguages(correctJson(moviesMetadatum.getSpokenLanguages()));
                metadatumRepo.save(moviesMetadatum);
                log.info("record {} updated", counter.addAndGet(1));
            });
        });
        log.info("background jobs scheduled");
    }

    private String correctJson(String json) {
        return json.replaceAll("'", "\"");
    }

    private String replaceJsonIfNotValid(String json) {
        int count = StringUtils.countMatches(json, "\"");
        if (count % 2 == 0) {
            return json;
        } else {
            return "[]";
        }
    }

    private String correctProductionCompaniesJson(String json) {
        String correctedJson = json;
        String[] arr = correctedJson.split("\"[a-zA-Z]*\":");
        for (String element : arr) {
            String correctedElement = element.replaceAll("\"", "");
            correctedJson = correctedJson.replace(element, correctedElement);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < correctedJson.length(); i++) {
            if (correctedJson.charAt(i) == ':') {
                builder.append(correctedJson.charAt(i));
                builder.append(' ');
                builder.append('"');
                i++;
                continue;
            }
            if ((correctedJson.charAt(i) == ',' && correctedJson.charAt(i - 1) != '}' && (correctedJson.charAt(i + 1) == '{' || correctedJson.charAt(i + 1) == ' '))
                    || correctedJson.charAt(i) == '}') {
                builder.append('"');
                builder.append(correctedJson.charAt(i));
                continue;
            }
            builder.append(correctedJson.charAt(i));
        }
        correctedJson = builder.toString();
        return correctedJson;
    }
}
