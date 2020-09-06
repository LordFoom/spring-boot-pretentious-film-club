package lordfoom.sideprojects.pretentiousfilmclub.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MovieReviewService {

    private final PageableCreator pageableCreator = new PageableCreator();
    @Autowired
    MovieReviewRepository movieReviewRepository;

    public Page<MovieReview> getPageOfMovieReviews(Integer pageNum, Integer pageSize, String sortBy, Sort.Direction direction) {
        Pageable page = pageableCreator.getPageable(pageNum, pageSize, sortBy, direction);
        return movieReviewRepository.findAll(page);
    }

}
