package no.domain.pm03;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Map<String, String> userDataMap = new HashMap<>();

    public void submitData(String username, String data) {
        userDataMap.put(username, data);
    }

    public String getUserData(String username) {
        return userDataMap.get(username);
    }
}