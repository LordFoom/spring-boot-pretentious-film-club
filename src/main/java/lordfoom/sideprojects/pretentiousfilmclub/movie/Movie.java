package lordfoom.sideprojects.pretentiousfilmclub.movie;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    String name;
    String description;
    Date released;
    @NotNull
    Date created = new Date();

    @OneToMany(mappedBy = "movie")
    Set<MovieReview> reviews;

}
