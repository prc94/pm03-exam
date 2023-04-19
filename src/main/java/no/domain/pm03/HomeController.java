package no.domain.pm03;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String root() {
        return "redirect:/submit";
    }

    @GetMapping("/get_user")
    @ResponseBody
    public String getUser(@CurrentSecurityContext(expression="authentication")
                              Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping("/submit")
    public ModelAndView submit(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("user", userDetails.getUsername());
        return new ModelAndView("forward:/submit.html", model.asMap());
    }
}