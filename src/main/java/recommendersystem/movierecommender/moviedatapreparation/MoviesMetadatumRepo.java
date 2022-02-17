package recommendersystem.movierecommender.moviedatapreparation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MoviesMetadatumRepo extends CrudRepository<MoviesMetadatum, UUID> {
    List<MoviesMetadatum> findAllByOrderById();

    @Query(nativeQuery = true, value = "select * from movies_metadata where movie_img_url isnull order by id;")
    List<MoviesMetadatum> findAllRemainingMovies();
}
