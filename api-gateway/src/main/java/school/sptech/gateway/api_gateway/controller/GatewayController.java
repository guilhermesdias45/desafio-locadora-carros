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
            @RequestBody(required = false) String body,
            ServerHttpRequest request
            ){

        String baseUrl = switch (service){
            case "carros", "modelos", "fabricantes", "acessorios" -> "http://localhost:8080";
            case "motoristas", "auth" -> "http://localhost:8081";
            case "alugueis" -> "http://localhost:8082";
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
                .headers(httpHeaders -> // httpHeaders.addAll(headers)
                {
                    headers.forEach((nome, valores) -> {
                        if (!nome.equalsIgnoreCase("host") && !nome.equalsIgnoreCase("content-length")) {
                            httpHeaders.addAll(nome, valores);
                        }
                    });
                })
                .bodyValue(body != null ? body : "")
                .retrieve()
                .toEntity(String.class);
    }
}
