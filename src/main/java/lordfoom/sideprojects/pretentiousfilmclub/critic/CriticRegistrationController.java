package lordfoom.sideprojects.pretentiousfilmclub.critic;

import lordfoom.sideprojects.pretentiousfilmclub.auth.CriticUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class CriticRegistrationController {

    @Autowired
    private CriticUserDetailsService criticService;

    @ModelAttribute("critic")
    public CriticRegistrationDto userRegistrationDto() {
        return new CriticRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model){
        return "registration";
    }

    @PostMapping
    public String registerCritic(@ModelAttribute("critic") @Valid CriticRegistrationDto dto, BindingResult result) {

        Optional<Critic> existing = criticService.findByUsername(dto.getUsername());
        if (existing.isPresent()) {
            result.rejectValue("username", null, "We already have that critic.");
        }
        if (result.hasErrors()) {
            return "registration";
        }

        criticService.save(dto);

        return "redirect:/registration?success";


    }
}
