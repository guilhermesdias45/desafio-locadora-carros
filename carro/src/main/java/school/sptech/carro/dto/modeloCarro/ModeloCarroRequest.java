package school.sptech.carro.dto.modeloCarro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import school.sptech.carro.model.Categoria;

public record ModeloCarroRequest(
        @NotBlank
        String descricao,

        @NotNull
        Categoria categoria,

        @NotNull
        Long fabricanteId
) {
}