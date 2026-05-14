package school.sptech.pessoa.dto;

import school.sptech.pessoa.model.enums.Sexo;

import java.time.LocalDate;

public record MotoristaResponseDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        Sexo sexo,
        String numeroCNH,
        Boolean ativo
) {}