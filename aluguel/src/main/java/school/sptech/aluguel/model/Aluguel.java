package school.sptech.aluguel.model;

import com.projeto.model.Motorista;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.carro.model.Carro;

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
    @Column(name = "apolice_id")
    private ApoliceSeguro apolice;
    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private com.projeto.model.Motorista motorista;
    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;


    public Aluguel(LocalDate dataEntrega, LocalDate dataDevolucao, BigDecimal valorTotal, ApoliceSeguro apolice, Motorista motorista, Carro carro) {
        this.dataEntrega = dataEntrega;
        this.dataDevolucao = dataDevolucao;
        this.valorTotal = valorTotal;
        this.apolice = apolice;
        this.motorista = motorista;
        this.carro = carro;
    }
}
