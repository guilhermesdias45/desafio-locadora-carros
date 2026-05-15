package school.sptech.gateway.api_gateway.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class GatewayController {
    private final WebClient webClient;

    public GatewayController() {
        this.webClient = WebClient.builder().build();
    }

    @RequestMapping("/{service}/**")
    public Mono<ResponseEntity<String>> proxy(
            @PathVariable String service,
            @RequestHeader HttpHeaders headers,
            @RequestParam(required = false)MultiValueMap<String, String> queryParams,
            @RequestBody(required = false) Mono<String> body,
            ServerHttpRequest request
            ){

        String baseUrl = switch (service){
            case "carros" -> "http://localhost:8080";
            case "motoristas" -> "http://localhost:8081";
            case "alugueis" -> "http://localhost:8082";
            case "auth" -> "http://localhost:8081";
            default -> null;
        };

        if (baseUrl == null){
            return Mono.just(ResponseEntity.status(400).body("Serviço " + service + " não encontrado."));
        }

        String fullPath = request.getURI().getRawPath().replace("/api", "");

        String urlCompleta = (request.getURI().getRawQuery() != null) ?
                baseUrl + fullPath + "?" + request.getURI().getRawQuery() : baseUrl + fullPath;

        return webClient.method(request.getMethod())
                .uri(urlCompleta)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(body == null ? Mono.empty() : body, String.class)
                .retrieve()
                .toEntity(String.class);
    }
}
