package school.sptech.pessoa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "motorista")
@PrimaryKeyJoinColumn(name = "pessoa_id")
public class Motorista extends Pessoa {

    @NotBlank(message = "Número da CNH é obrigatório")
    @Column(name = "numero_cnh", nullable = false, unique = true)
    private String numeroCNH;

}