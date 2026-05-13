package school.sptech.aluguel.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AluguelRequestDTO(
        @PastOrPresent
        LocalDate dataEntrega,
        @FutureOrPresent
        LocalDate dataDevolucao,
        @Positive
        BigDecimal valorTotal,
        @NotNull
        ApoliceSeguroRequest apolice,
        @Positive
        Long motoristaId,
        @Positive
        Long carroId
) {
    public record ApoliceSeguroRequest(
            @Positive
            BigDecimal valorFranquia,
            @NotNull
            Boolean protecaoTerceiro,
            @NotNull
            Boolean protecaoCausasNaturais,
            @NotNull
            Boolean protecaoRoubo
    ){ }
}