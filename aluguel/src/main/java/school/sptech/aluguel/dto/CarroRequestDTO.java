package school.sptech.aluguel.dto;

import java.math.BigDecimal;

public record CarroRequestDTO(
        Long id,
        String placa,
        String chassi,
        String cor,
        BigDecimal valorDiaria
) {}
