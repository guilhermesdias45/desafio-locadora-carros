package school.sptech.carro.dto.fabricante;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FabricanteRequest(
        @NotBlank
        String nome
) {
}
