package school.sptech.aluguel.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder().filter(addAuthorizationHeader());
    }

    private ExchangeFilterFunction addAuthorizationHeader() {
        return (request, next) -> Mono.deferContextual(contextView -> {
            String token = contextView.getOrDefault("AUTH_TOKEN", null);

            if (token != null && !token.isEmpty()) {
                ClientRequest newRequest = ClientRequest.from(request)
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .build();
                return next.exchange(newRequest);
            }

            return next.exchange(request);
        });
    }
}