package school.sptech.aluguel.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AluguelResponseDTO (
        Long id,
        LocalDateTime dataPedido,
        LocalDate dataEntrega,
        LocalDate dataDevolucao,
        BigDecimal valorTotal,
        ApoliceSeguroResponse apolice,
        Long motoristaId,
        Long carroId
){
    public record ApoliceSeguroResponse(
            Long id,
            BigDecimal valorFranquia,
            Boolean protecaoTerceiro,
            Boolean protecaoCausasNaturais,
            Boolean protecaoRoubo
    ){}
}
