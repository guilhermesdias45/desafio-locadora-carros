package school.sptech.pessoa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record   UsuarioRequestDTO(
        @NotBlank String login,
        @NotBlank @Size(min = 5) String senha
) {}
