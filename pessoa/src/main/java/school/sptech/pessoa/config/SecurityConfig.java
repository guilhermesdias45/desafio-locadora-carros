package school.sptech.pessoa.config;

import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Configuration
@OpenAPIDefinition(security = @SecurityRequirement(name = "BearerAuth"))
@io.swagger.v3.oas.annotations.security.SecurityScheme(
        name = "BearerAuth", type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"
)
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String secretKeyPlain;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml"
                        ).permitAll()
                        .requestMatchers(
                                "/auth",
                                "/auth/autenticar"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new OncePerRequestFilter() {
                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                            throws ServletException, IOException {

                        String authHeader = request.getHeader("Authorization");

                        if (authHeader != null && authHeader.startsWith("Bearer ")) {
                            try {
                                String token = authHeader.substring(7);

                                String username = Jwts.parser()
                                        .verifyWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKeyPlain.getBytes(StandardCharsets.UTF_8)))
                                        .build()
                                        .parseSignedClaims(token)
                                        .getPayload()
                                        .getSubject();

                                if (username != null) {
                                    UsernamePasswordAuthenticationToken auth =
                                            new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                                    SecurityContextHolder.getContext().setAuthentication(auth);
                                }
                            } catch (Exception e) {
                                SecurityContextHolder.clearContext();
                            }
                        }
                        filterChain.doFilter(request, response);
                    }
                }, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
