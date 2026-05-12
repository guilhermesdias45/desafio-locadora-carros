package school.sptech.aluguel.model;

import com.projeto.model.Motorista;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Calendar dataPedido;
    @PastOrPresent
    private Date dataEntrega;
    @FutureOrPresent
    private Date dataDevolucao;
    @Positive
    private BigDecimal valorTotal;
    @OneToOne
    @Column(name = "apolice_id")
    private ApoliceSeguro apolice;
    @ManyToOne
    private com.projeto.model.Motorista motorista;
    @ManyToOne
    private Carro carro;


    public Aluguel(Date dataEntrega, Date dataDevolucao, BigDecimal valorTotal, ApoliceSeguro apolice, Motorista motorista, Carro carro) {
        this.dataEntrega = dataEntrega;
        this.dataDevolucao = dataDevolucao;
        this.valorTotal = valorTotal;
        this.apolice = apolice;
        this.motorista = motorista;
        this.carro = carro;
    }
}
