package lordfoom.sideprojects.pretentiousfilmclub.movie;

import lordfoom.sideprojects.pretentiousfilmclub.critic.Critic;
import lordfoom.sideprojects.pretentiousfilmclub.critic.CriticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/movies")
public class MovieHomeController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieReviewRepository movieReviewRepository;

    @Autowired
    MovieService movieService;
    @Autowired
    CriticRepository criticRepository;

//    void addModelAttributes(Model model) {
//        model.addAttribute("module", "movies");
//    }

    @ModelAttribute("module")
    String module() {
        return "movies";
    }

    @ModelAttribute("movie")
    MovieDto getAddMovieDto() {
        return new MovieDto();
    }

    @GetMapping(path = "/add")
    String getAddMoviePage(Model model) {
        return "movies/movie_add";
    }

    @GetMapping
    String getMovieListingPage(Model model, @RequestParam("page") Optional<Integer> page, Optional<Integer> size, Optional<String> sortBy, Optional<String> direction) {

        int currPage = page.orElse(0);
        int pageSize = size.orElse(10);
        String sort = sortBy.orElse("released");
        String drct = direction.orElse("desc");

        Sort.Direction sortDirection = Sort.Direction.fromString(drct);


//        model.addAttribute("movies", movieRepository.findAll());
        model.addAttribute("movies", movieService.getMoviePage(currPage, pageSize, sort, sortDirection));
        return "movies/movie_home";
    }

    @PostMapping(path = "/add")
    String addMovie(@ModelAttribute("movie") @Valid MovieDto addMovieDto, BindingResult result) {
        Optional<Movie> dupe = movieRepository.findByName(addMovieDto.getName());
        if (dupe.isPresent())
            result.rejectValue("name", "This movie is already in the database :)");
        if (result.hasErrors())
            return "movies/movie_add";

        movieService.save(addMovieDto);
        return "redirect:/movies/add?success";
    }

    @GetMapping(path = "/{movieId}/reviews")
    String getViewMovieReviewPages(Model model, @PathVariable("movieId") Long movieId) {
        Optional<Movie> movieToReview = movieRepository.findById(movieId);
        if(!movieToReview.isPresent())
            return "redirect:/movies?movieMissing";

        movieToReview.ifPresent(model::addAttribute);

        Set<MovieReview> reviews = movieReviewRepository.findAllByMovieId(movieToReview.get().getId());

        model.addAttribute("reviews", reviews);

        return "reviews/movie_reviews";


    }

    @GetMapping(path = "/{movieId}/review")
    String getAddReviewPage(Model model, @PathVariable("movieId") Long movieId) {
        Optional<Movie> movieToReview = movieRepository.findById(movieId);
        if (!movieToReview.isPresent()) {
            return "redirect:/home/home_signed_in";
        }
        movieToReview.ifPresent(model::addAttribute);
        model.addAttribute("review", new MovieReviewDto());
        return "reviews/review_add";
    }

    @PostMapping(path = "/{movieId}/review")
    String getAddReviewPage(@ModelAttribute("review") @Valid MovieReviewDto movieReviewDto, BindingResult result, @PathVariable("movieId") Long movieId) {
        Optional<Movie> movieToReview = getMovieOrAddError(movieId, result);
        if(!movieToReview.isPresent())
            return "redirect:/movies?movieMissing";
        if(result.hasErrors())
            return "reviews/review_add";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authName = auth.getName();

        Optional<Critic> criticOptional = criticRepository.findByUsername(authName);

        if (!criticOptional.isPresent()) {
            result.reject("NoCritic", "Critic does not exist!");
            return "home/signed_out";
        }
        Optional<MovieReview> dupe = movieReviewRepository.findById(new MovieReviewKey(criticOptional.get().getId(), movieId));
        if (dupe.isPresent()) {
           result.reject("You have already reviewed this movie");
           return "reviews/review_add";
        }

        movieService.saveReview(movieReviewDto, criticOptional.get(), movieToReview.get());
        return "redirect:/movies/"+movieId+"/review?success";
    }

    private Optional<Movie> getMovieOrAddError(Long movieId, BindingResult result) {
        Optional<Movie> movieToReview = movieRepository.findById(movieId);
        if (!movieToReview.isPresent()) {
            result.reject("Movie does not exist");
        }
        return movieToReview;
    }
}
