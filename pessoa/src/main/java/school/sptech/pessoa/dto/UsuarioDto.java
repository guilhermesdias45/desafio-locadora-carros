package school.sptech.pessoa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDto(
        @NotBlank
        String login,
        @NotBlank
        @Size(min = 5)
        String senha
) { }
