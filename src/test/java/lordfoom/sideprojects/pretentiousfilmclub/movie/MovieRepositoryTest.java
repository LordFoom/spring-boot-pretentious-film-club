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

@SpringBootTest
@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @BeforeEach
    void setup() {
        LocalDate localReleaseDate = LocalDate.of(2020, 8, 24);
        Date releaseDate = Date.from(localReleaseDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Movie movie = Movie.builder()
                           .description("Test movie Review")
                           .name("Foom's Furious Funride")
                           .id(1L)
                           .created(new Date())
                           .released(releaseDate).build();
        entityManager.persist(movie);
        entityManager.flush();
    }

    @Test
    void findAllByNameContaining() {

    }

    @Test
    void findByName() {
    }
}