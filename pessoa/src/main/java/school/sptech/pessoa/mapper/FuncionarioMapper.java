package school.sptech.pessoa.mapper;

import org.springframework.stereotype.Component;
import school.sptech.pessoa.dto.FuncionarioRequestDTO;
import school.sptech.pessoa.dto.FuncionarioResponseDTO;
import school.sptech.pessoa.model.Funcionario;

@Component
public class FuncionarioMapper {

    public Funcionario toEntity(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.pessoa().nome());
        funcionario.setDataNascimento(dto.pessoa().dataNascimento());
        funcionario.setCpf(dto.pessoa().cpf());
        funcionario.setEmail(dto.pessoa().email());
        funcionario.setSexo(dto.pessoa().sexo());
        funcionario.setMatricula(dto.matricula());
        funcionario.setAtivo(true);
        return funcionario;
    }

    public FuncionarioResponseDTO toResponseDTO(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getDataNascimento(),
                funcionario.getCpf(),
                funcionario.getEmail(),
                funcionario.getSexo(),
                funcionario.getMatricula(),
                funcionario.getAtivo()
        );
    }

    public void updateEntityFromDTO(FuncionarioRequestDTO dto, Funcionario funcionario) {
        funcionario.setNome(dto.pessoa().nome());
        funcionario.setDataNascimento(dto.pessoa().dataNascimento());
        funcionario.setCpf(dto.pessoa().cpf());
        funcionario.setEmail(dto.pessoa().email());
        funcionario.setSexo(dto.pessoa().sexo());
        funcionario.setMatricula(dto.matricula());
    }
}