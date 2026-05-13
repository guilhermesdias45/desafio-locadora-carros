package school.sptech.carro.dto.carro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.List;

public record CarroRequest(
        @NotBlank
        String placa,

        @NotBlank
        String chassi,

        @NotBlank
        String cor,

        @NotNull
        @PositiveOrZero
        BigDecimal valorDiaria,

        @NotNull
        Long modeloCarroId,

        @NotNull
        List<Long> acessorioIds
) {
}
