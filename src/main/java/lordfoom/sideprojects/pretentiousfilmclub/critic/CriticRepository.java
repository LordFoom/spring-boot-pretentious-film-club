package lordfoom.sideprojects.pretentiousfilmclub.critic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CriticRepository extends JpaRepository<Critic, Integer> {

    Optional<Critic> findByUsername(String username);
}
