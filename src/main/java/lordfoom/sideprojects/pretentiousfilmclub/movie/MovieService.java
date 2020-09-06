package lordfoom.sideprojects.pretentiousfilmclub.movie;

import lordfoom.sideprojects.pretentiousfilmclub.critic.Critic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieReviewRepository movieReviewRepository;

    private final PageableCreator pageableCreator = new PageableCreator();


    public Movie save(MovieDto dto) {
        Movie movie = new Movie();
        movie.setName(dto.getName());
        movie.setDescription(dto.getDescription());
        movie.setReleased(dto.getReleased());
        movie.setCreated(new Date());
        return movieRepository.save(movie);
    }

    public Page<Movie> getMoviePage(Integer pageNum, Integer pageSize, String sortBy, Sort.Direction direction) {
        Pageable page = pageableCreator.getPageable(pageNum, pageSize, sortBy, direction);
        return movieRepository.findAll(page);
    }

    public MovieReview saveReview(MovieReviewDto movieDto, Critic critic, Movie movie) {
        MovieReview movieReview = new MovieReview();
        movieReview.setKey(new MovieReviewKey(critic.getId(), movie.getId()));
        movieReview.setRating(movieDto.getRating());
        movieReview.setText(movieDto.getText());

        return movieReviewRepository.save(movieReview);
    }
}
