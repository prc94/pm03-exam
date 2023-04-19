package no.domain.pm03;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private Map<String, List<TourData>> dataMap = new HashMap<>();

    @PostMapping(value = "/submit", consumes = "application/json")
    public void submitData(@RequestBody TourData tourData,
                           @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        dataMap.computeIfAbsent(authentication.getName(), (e) -> new LinkedList<>()).add(tourData);
    }

    @GetMapping(value = "/userdata", produces = "application/json")
    public ResponseEntity<List<TourData>> getUserData(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        return ResponseEntity.of(Optional.ofNullable(dataMap.get(authentication.getName())));
    }

}
