package lordfoom.sideprojects.pretentiousfilmclub.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/reviews")
public class MovieReviewController {

    @Autowired
    MovieReviewService movieReviewService;

    @GetMapping
    String getMovieReviewListingPage(Model model, @RequestParam("page") Optional<Integer> page, Optional<Integer> size, Optional<String> sortBy, Optional<String> direction) {

        int currPage = page.orElse(0);
        int pageSize = size.orElse(10);
        String sort = sortBy.orElse("created");
        String drct = direction.orElse("desc");
        Sort.Direction sortDirection = Sort.Direction.fromString(drct);
        model.addAttribute("reviews", movieReviewService.getPageOfMovieReviews(currPage, pageSize, sort, sortDirection));
        return "reviews/review_home";
    }
}
