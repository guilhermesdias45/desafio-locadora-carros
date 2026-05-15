package school.sptech.pessoa.mapper;

import org.springframework.stereotype.Component;
import school.sptech.pessoa.dto.MotoristaRequestDTO;
import school.sptech.pessoa.dto.MotoristaResponseDTO;
import school.sptech.pessoa.model.Motorista;

@Component
public class MotoristaMapper {

    public Motorista toEntity(MotoristaRequestDTO dto) {
        Motorista motorista = new Motorista();
        motorista.setNome(dto.nome());
        motorista.setDataNascimento(dto.dataNascimento());
        motorista.setCpf(dto.cpf());
        motorista.setEmail(dto.email());
        motorista.setSexo(dto.sexo());
        motorista.setNumeroCNH(dto.numeroCNH());
        motorista.setAtivo(true);
        return motorista;
    }

    public MotoristaResponseDTO toResponseDTO(Motorista motorista) {
        return new MotoristaResponseDTO(
                motorista.getId(),
                motorista.getNome(),
                motorista.getDataNascimento(),
                motorista.getCpf(),
                motorista.getEmail(),
                motorista.getSexo(),
                motorista.getNumeroCNH(),
                motorista.getAtivo()
        );
    }

    public void updateEntityFromDTO(MotoristaRequestDTO dto, Motorista motorista) {
        motorista.setNome(dto.nome());
        motorista.setDataNascimento(dto.dataNascimento());
        motorista.setCpf(dto.cpf());
        motorista.setEmail(dto.email());
        motorista.setSexo(dto.sexo());
        motorista.setNumeroCNH(dto.numeroCNH());
    }
}