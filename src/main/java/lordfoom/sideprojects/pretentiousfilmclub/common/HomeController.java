package lordfoom.sideprojects.pretentiousfilmclub.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class HomeController {

    @ModelAttribute("module")
    String module() {
        return "home";
    }

    @GetMapping("/")
    String index( Principal principal){
//        model.addAttribute("module", "home");
        return null != principal ? "home/home_signed_in" :"home/home_signed_out";
    }

    @GetMapping("/login")
    String login(){
        return "login";
    }

    @GetMapping("/logout")
    String logout(){
        return "logout";
    }
}
