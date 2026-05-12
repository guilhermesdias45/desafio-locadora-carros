package school.sptech.pessoa.dto;

import com.projeto.model.Motorista;
import school.sptech.pessoa.model.enums.Sexo;

import java.time.LocalDate;

public record MotoristaResponseDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        Sexo sexo,
        String numeroCNH
) {
    public static MotoristaResponseDTO fromEntity(Motorista motorista) {
        return new MotoristaResponseDTO(
                motorista.getId(),
                motorista.getNome(),
                motorista.getDataNascimento(),
                motorista.getCpf(),
                motorista.getSexo(),
                motorista.getNumeroCNH()
        );
    }
}