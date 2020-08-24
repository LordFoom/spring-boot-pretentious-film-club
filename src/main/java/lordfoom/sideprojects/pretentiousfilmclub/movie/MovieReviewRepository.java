package lordfoom.sideprojects.pretentiousfilmclub.movie;

import lordfoom.sideprojects.pretentiousfilmclub.critic.Critic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MovieReviewRepository extends JpaRepository<MovieReview, MovieReviewKey> {

    public Set<MovieReview> findByMovie(Movie movie);
    public Set<MovieReview> findByMovieId(Long movieId);
    public Set<MovieReview> findByCriticId(Long criticId);
    public Set<MovieReview> findByCritic(Critic critic);

    public Set<MovieReview> findAllByMovieId(Long id);
}
