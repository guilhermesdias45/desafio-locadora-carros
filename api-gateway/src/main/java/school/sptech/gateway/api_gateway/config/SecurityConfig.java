package school.sptech.gateway.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String secretKeyBase64;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http){
        http.csrf(ServerHttpSecurity.CsrfSpec::disable).authorizeExchange(
                exchanges -> exchanges.pathMatchers(
                                "/api/auth/**", "/api/auth",
                        "/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll().anyExchange().authenticated()
                ).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtDecoder(reactiveJwtDecoder())));

        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        try {
            byte[] secretBytes = Base64.getDecoder().decode(this.secretKeyBase64.trim());
            SecretKeySpec secretKey = new SecretKeySpec(secretBytes, "HmacSHA512");

            return NimbusReactiveJwtDecoder.withSecretKey(secretKey)
                    .macAlgorithm(MacAlgorithm.HS512).build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Chave secreta inválida. Verifique a configuração em jwt.secret", e);
        }
    }
}
