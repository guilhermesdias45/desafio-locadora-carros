package school.sptech.aluguel.dto;

import lombok.Getter;
import lombok.Setter;
import school.sptech.aluguel.model.ApoliceSeguro;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AluguelRequestDTO {
    private Date dataEntrega;
    private Date dataDevolucao;
    private BigDecimal valorTotal;
    private ApoliceSeguro apolice;
    private Long motoristaId;
    private Long carroId;
}
