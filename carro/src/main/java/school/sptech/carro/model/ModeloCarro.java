package school.sptech.carro.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "modelo_carro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ModeloCarro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private Fabricante fabricante;
}
