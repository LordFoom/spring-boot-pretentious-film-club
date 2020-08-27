package lordfoom.sideprojects.pretentiousfilmclub.movie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
@DataJpaTest
class MovieRepositoryTest {

    public static final String FOOM_S_FURIOUS_FUNRIDE = "Foom's Furious Funride";
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    MovieRepository movieRepository;

    @BeforeEach
    void setup() {
//        LocalDate localReleaseDate = LocalDate.of(2020, 8, 24);
//        Date releaseDate = Date.from(localReleaseDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//        Movie movie = Movie.builder()
//                           .description("Test movie Review")
//                           .name(FOOM_S_FURIOUS_FUNRIDE)
////                           .id(1L)
//                           .created(new Date())
//                           .released(releaseDate).build();
////        entityManager.persist(movie);
//        movieRepository.save(movie);
//        Movie movie_twee = Movie.builder()
//                           .description("This movie makes the double grade")
//                           .name("The Semantic Window Breaker")
////                           .id(2L)
//                           .created(new Date())
//                           .released(releaseDate).build();
//        movieRepository.save(movie);
////        entityManager.persist(movie_twee);
//
////        entityManager.flush();
    }

    @Test
    void findAllByNameContaining() {
        LocalDate localReleaseDate = LocalDate.of(2020, 8, 24);
        Date releaseDate = Date.from(localReleaseDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Movie movie = Movie.builder()
                           .description("Test movie Review")
                           .name(FOOM_S_FURIOUS_FUNRIDE)
//                           .id(1L)
                           .created(new Date())
                           .released(releaseDate).build();
        entityManager.persist(movie);
//        movieRepository.save(movie);
        List<Movie> movieList = movieRepository.findAllByNameContaining("Foom");
        assertEquals(movieList.size(), 1);
        Movie movieRetrieved = movieList.get(0);

        assertEquals(FOOM_S_FURIOUS_FUNRIDE, movieRetrieved.getName());

   }

    @Test
    void findByName() {
    }
}