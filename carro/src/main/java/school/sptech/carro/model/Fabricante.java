package school.sptech.carro.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fabricante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}