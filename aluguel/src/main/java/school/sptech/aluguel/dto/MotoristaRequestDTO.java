package school.sptech.aluguel.dto;

import java.time.LocalDate;

public record MotoristaRequestDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        String sexo,
        String numeroCNH
) {}
