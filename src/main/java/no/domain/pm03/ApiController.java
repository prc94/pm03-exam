package no.domain.pm03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/submit")
    public void submitData(@RequestBody UserData userData, @RequestParam String username) {
        userService.submitData(username, userData.getData());
    }

    @GetMapping("/userdata")
    public String getUserData(@RequestParam String username) {
        return userService.getUserData(username);
    }

}
