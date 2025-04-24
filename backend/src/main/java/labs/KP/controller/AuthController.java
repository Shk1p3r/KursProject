package labs.KP.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import labs.KP.pojo.LoginPojo;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginPojo loginRequest, HttpServletRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()));

            // Установим авторизацию
            SecurityContextHolder.getContext().setAuthentication(auth);

            // 💾 ОБЯЗАТЕЛЬНО: создать HTTP-сессию вручную
            HttpSession session = request.getSession(true); // создаёт сессию
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            String role = auth.getAuthorities().stream()
                    .findFirst()
                    .map(a -> a.getAuthority().replace("ROLE_", ""))
                    .orElse("UNKNOWN");

            return Map.of("role", role);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid credentials");
        }
    }

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
