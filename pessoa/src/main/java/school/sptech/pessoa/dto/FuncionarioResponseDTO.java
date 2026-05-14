package school.sptech.pessoa.dto;

import school.sptech.pessoa.model.Funcionario;
import school.sptech.pessoa.model.enums.Sexo;

import java.time.LocalDate;

public record FuncionarioResponseDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        Sexo sexo,
        String matricula,
        Boolean ativo
) {}