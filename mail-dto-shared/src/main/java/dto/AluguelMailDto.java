package dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AluguelMailDto(
        LocalDate dataPedido,
        LocalDate dataEntrega,
        LocalDate dataDevolucao,
        BigDecimal valorTotal,

        ApoliceSeguro apolice,
        MotoristaMailDto motorista,
        CarroMailDto carro
) {
    public record ApoliceSeguro(
            BigDecimal valorFranquia,
            Boolean protecaoTerceiro,
            Boolean protecaoCausasNaturais,
            Boolean protecaoRoubo
    ) {
    }
}
