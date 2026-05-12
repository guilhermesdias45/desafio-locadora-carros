package school.sptech.aluguel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.pessoa.dto.MotoristaResponseDTO;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AluguelResponseDTO {

    private Long id;
    private Calendar dataPedido;
    private Date dataEntrega;
    private Date dataDevolucao;
    private BigDecimal valorTotal;
    private ApoliceSeguroResponse apolice;
    private MotoristaResponseDTO motorista;
    private CarroResponseDTO carro;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class ApoliceSeguroResponse{
        private BigDecimal valorFranquia;
        private Boolean protecaoTerceiro;
        private Boolean protecaoCausasNaturais;
        private Boolean protecaoRoubo;
    }
}
