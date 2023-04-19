package no.domain.pm03;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private Map<String, String> dataMap = new HashMap<>();

    @PostMapping(value = "/submit", consumes = "application/json")
    public void submitData(@RequestBody UserData userData,
                           @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        dataMap.put(authentication.getName(), userData.getData());
    }

    @GetMapping("/userdata")
    public String getUserData(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        return dataMap.get(authentication.getName());
    }

}
