package lordfoom.sideprojects.pretentiousfilmclub.critic;

import lombok.Data;
import lombok.NoArgsConstructor;
import lordfoom.sideprojects.pretentiousfilmclub.movie.MovieReview;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "critic")
public class Critic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    String username;

    @NotNull
    String password;

    @OneToMany(mappedBy = "critic")
    private Collection<MovieReview> movieReview;

    public Collection<MovieReview> getMovieReview() {
        return movieReview;
    }

    public void setMovieReview(Collection<MovieReview> movieReview) {
        this.movieReview = movieReview;
    }
}
