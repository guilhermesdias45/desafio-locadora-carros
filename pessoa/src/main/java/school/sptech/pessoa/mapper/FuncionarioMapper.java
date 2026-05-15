package school.sptech.pessoa.mapper;

import org.springframework.stereotype.Component;
import school.sptech.pessoa.dto.FuncionarioRequestDTO;
import school.sptech.pessoa.dto.FuncionarioResponseDTO;
import school.sptech.pessoa.model.Funcionario;

@Component
public class FuncionarioMapper {

    public Funcionario toEntity(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setDataNascimento(dto.dataNascimento());
        funcionario.setCpf(dto.cpf());
        funcionario.setEmail(dto.email());
        funcionario.setSexo(dto.sexo());
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
        funcionario.setNome(dto.nome());
        funcionario.setDataNascimento(dto.dataNascimento());
        funcionario.setCpf(dto.cpf());
        funcionario.setEmail(dto.email());
        funcionario.setSexo(dto.sexo());
        funcionario.setMatricula(dto.matricula());
    }
}