package school.sptech.aluguel.dto;

import school.sptech.aluguel.model.ApoliceSeguro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AluguelRequestDTO(
        LocalDate dataEntrega,
        LocalDate dataDevolucao,
        BigDecimal valorTotal,
        ApoliceSeguro apolice,
        Long motoristaId,
        Long carroId
) {}