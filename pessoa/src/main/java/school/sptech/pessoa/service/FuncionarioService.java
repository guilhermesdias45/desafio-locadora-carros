package school.sptech.pessoa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.pessoa.dto.FuncionarioRequestDTO;
import school.sptech.pessoa.dto.FuncionarioResponseDTO;
import school.sptech.pessoa.mapper.FuncionarioMapper;
import school.sptech.pessoa.model.Funcionario;
import school.sptech.pessoa.repository.FuncionarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map(funcionarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioResponseDTO buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id: " + id));
        return funcionarioMapper.toResponseDTO(funcionario);
    }

    public FuncionarioResponseDTO buscarPorCpf(String cpf) {
        Funcionario funcionario = funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com CPF: " + cpf));
        return funcionarioMapper.toResponseDTO(funcionario);
    }

    public FuncionarioResponseDTO criar(FuncionarioRequestDTO dto) {
        if (funcionarioRepository.existsByCpf(dto.cpf())) {
            throw new RuntimeException("Já existe um funcionário com o CPF: " + dto.cpf());
        }
        if (funcionarioRepository.existsByMatricula(dto.matricula())) {
            throw new RuntimeException("Já existe um funcionário com a matrícula: " + dto.matricula());
        }

        Funcionario funcionario = funcionarioMapper.toEntity(dto);
        return funcionarioMapper.toResponseDTO(funcionarioRepository.save(funcionario));
    }

    public FuncionarioResponseDTO atualizar(Long id, FuncionarioRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id: " + id));

        if (!funcionario.getCpf().equals(dto.cpf()) && funcionarioRepository.existsByCpf(dto.cpf())) {
            throw new RuntimeException("Já existe um funcionário com o CPF: " + dto.cpf());
        }
        if (!funcionario.getMatricula().equals(dto.matricula()) && funcionarioRepository.existsByMatricula(dto.matricula())) {
            throw new RuntimeException("Já existe um funcionário com a matrícula: " + dto.matricula());
        }

        funcionarioMapper.updateEntityFromDTO(dto, funcionario);
        return funcionarioMapper.toResponseDTO(funcionarioRepository.save(funcionario));
    }

    public void deletar(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado com id: " + id);
        }
        funcionarioRepository.deleteById(id);
    }
}