package labs.KP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {
    @Value("${security.logins}")
    private String logins;

    @Value("${security.passwords}")
    private String passwords;

    @Value("${security.roles}")
    private String roles;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        String[] loginArray = logins.split(",");
        String[] passwordArray = passwords.split(",");
        String[] roleArray = roles.split(",");

        List<UserDetails> users = new ArrayList<>();

        for (int i = 0; i < loginArray.length; i++) {
            users.add(
                    User.withUsername(loginArray[i].trim())
                            .password(passwordEncoder().encode(passwordArray[i].trim()))
                            .roles(roleArray[i].trim())
                            .build());
        }

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/", "/home", "/api/login", "/api/me").permitAll()
                .requestMatchers("/students/**").hasAnyRole("DIRECTOR", "INSTRUCTOR", "STUDENT")
                .requestMatchers("/cars/**").hasAnyRole("DIRECTOR", "INSTRUCTOR")
                .requestMatchers("/instructors/**").hasAnyRole("DIRECTOR", "INSTRUCTOR")
                .requestMatchers("/categories/**", "/exams/**", "/lessons/**").hasRole("DIRECTOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/login")
                .defaultSuccessUrl("/api/me", true)
                .permitAll()
                .and()
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                        .permitAll());

        return http.build();
    }
}
