package no.domain.pm03;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void onInit() {
        String username = "user";
        String password = "123qweQWE";
        var user = new Account(username, passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @GetMapping("/")
    public RedirectView root() {
        return new RedirectView("/index.html");
    }

    @PostMapping("/login")
    public String login(@RequestBody Account user) {
        Account existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null || !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Welcome " + authentication.getName();
    }

    @GetMapping("/submit")
    public String submit(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Account user = userRepository.findByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        return "submit";
    }
}