package lordfoom.sideprojects.pretentiousfilmclub.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MovieReviewKey implements Serializable {


    @NotNull
    @Column(name = "critic_id")
    Long criticId;

    @NotNull
    @Column(name = "movie_id")
    Long movieId;
}
