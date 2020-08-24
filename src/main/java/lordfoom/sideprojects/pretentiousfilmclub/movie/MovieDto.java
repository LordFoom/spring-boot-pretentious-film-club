package lordfoom.sideprojects.pretentiousfilmclub.movie;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class MovieDto {
    @NotEmpty
    String name;
    @NotEmpty
    String description;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date released;
}
