package lordfoom.sideprojects.pretentiousfilmclub.movie;

import lombok.*;
import lordfoom.sideprojects.pretentiousfilmclub.critic.Critic;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "movie_review")
public class MovieReview {

    @EmbeddedId
    MovieReviewKey key;

    @ManyToOne
    @MapsId("critic_id")
    @JoinColumn(name = "critic_id")
    Critic critic;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    Movie movie;

    @NotNull
    double rating;

    @NotNull
    Date created = new Date();

    @NotNull
    @Lob
    String text;

    @Override
    public String toString() {
        return critic.getUsername()+":"+text;
    }
}
