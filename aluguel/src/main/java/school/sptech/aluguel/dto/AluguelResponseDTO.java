package school.sptech.aluguel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.pessoa.dto.MotoristaResponseDTO;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public record AluguelResponseDTO (
        Long id,
        Calendar dataPedido,
        Date dataEntrega,
        Date dataDevolucao,
        BigDecimal valorTotal,
        ApoliceSeguroResponse apolice,
        MotoristaResponseDTO motorista,
        CarroResponseDTO carro
){
    public record ApoliceSeguroResponse(
            BigDecimal valorFranquia,
            Boolean protecaoTerceiro,
            Boolean protecaoCausasNaturais,
            Boolean protecaoRoubo
    ){ }
}
