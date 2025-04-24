package labs.KP.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {
    @GetMapping("/me")
    public Map<String, String> getCurrentUser(Authentication auth) {
        String role = auth.getAuthorities().stream()
                          .findFirst()
                          .map(GrantedAuthority::getAuthority)
                          .orElse("UNKNOWN")
                          .replace("ROLE_", "");

        return Map.of("role", role);
    }
}
