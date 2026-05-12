package school.sptech.aluguel.aluguel;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.aluguel.apolice.ApoliceSeguro;

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
//    @ManyToOne
//    private Motorista motorista;
//    @ManyToOne
//    private Carro carro;
}
