package lordfoom.sideprojects.pretentiousfilmclub.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {


    List<Movie> findAllByNameContaining(String name);
    Optional<Movie> findByName(String name);
}
