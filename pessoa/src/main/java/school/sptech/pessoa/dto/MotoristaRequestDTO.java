package school.sptech.pessoa.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MotoristaRequestDTO(
        @Valid
        @NotNull(message = "Dados pessoais são obrigatórios")
        PessoaRequestDTO pessoa,

        @NotBlank(message = "Número da CNH é obrigatório")
        String numeroCNH
) {}