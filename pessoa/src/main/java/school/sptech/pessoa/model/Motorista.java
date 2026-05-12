package com.projeto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import school.sptech.pessoa.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "motorista", fetch = FetchType.LAZY)
    private List<Aluguel> alugueis = new ArrayList<>();
}