package school.sptech.pessoa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.pessoa.model.enums.Sexo;

import java.time.LocalDate;

public record FuncionarioRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Data de nascimento é obrigatória")
        LocalDate dataNascimento,

        @NotBlank(message = "CPF é obrigatório")
        @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
        String cpf,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        String email,

        @NotNull(message = "Sexo é obrigatório")
        Sexo sexo,

        @NotBlank(message = "Matrícula é obrigatória")
        String matricula
) {}
