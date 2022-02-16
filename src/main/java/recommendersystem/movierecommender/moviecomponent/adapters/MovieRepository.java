package recommendersystem.movierecommender.moviecomponent.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface MovieRepository extends JpaRepository<MovieRecord, UUID> {
}
