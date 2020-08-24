package lordfoom.sideprojects.pretentiousfilmclub.movie;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MovieReviewDto {

    @NotNull
    Double rating;
    @NotEmpty
    String text;


}
