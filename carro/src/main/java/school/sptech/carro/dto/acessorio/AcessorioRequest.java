package school.sptech.carro.dto.acessorio;

import jakarta.validation.constraints.NotBlank;

public record AcessorioRequest(
        @NotBlank
        String descricao
) {
}
