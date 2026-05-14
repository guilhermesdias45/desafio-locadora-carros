package school.sptech.aluguel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataPedido;
    private LocalDate dataEntrega;
    private LocalDate dataDevolucao;
    private BigDecimal valorTotal;
    @OneToOne
    @JoinColumn(name = "apolice_id")
    private ApoliceSeguro apolice;
    @JoinColumn(name = "motorista_id")
    private Long motoristaId;
    @JoinColumn(name = "carro_id")
    private Long carroId;


    public Aluguel(LocalDate dataEntrega, LocalDate dataDevolucao, BigDecimal valorTotal, ApoliceSeguro apolice, Long motorista, Long carro) {
        this.dataEntrega = dataEntrega;
        this.dataDevolucao = dataDevolucao;
        this.valorTotal = valorTotal;
        this.apolice = apolice;
        this.motoristaId = motorista;
        this.carroId = carro;
    }
}
