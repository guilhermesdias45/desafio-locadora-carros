package school.sptech.pessoa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.pessoa.dto.FuncionarioRequestDTO;
import school.sptech.pessoa.dto.FuncionarioResponseDTO;
import school.sptech.pessoa.exception.BusinessException;
import school.sptech.pessoa.exception.ResourceNotFoundException;
import school.sptech.pessoa.mapper.FuncionarioMapper;
import school.sptech.pessoa.model.Funcionario;
import school.sptech.pessoa.repository.FuncionarioRepository;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public Page<FuncionarioResponseDTO> listarTodos(Pageable pageable) {
        return funcionarioRepository.findAllByAtivoTrue(pageable)
                .map(funcionarioMapper::toResponseDTO);
    }

    public FuncionarioResponseDTO buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .filter(Funcionario::getAtivo)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + id));
        return funcionarioMapper.toResponseDTO(funcionario);
    }

    public FuncionarioResponseDTO buscarPorCpf(String cpf) {
        Funcionario funcionario = funcionarioRepository.findByCpfAndAtivoTrue(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com CPF: " + cpf));
        return funcionarioMapper.toResponseDTO(funcionario);
    }

    @Transactional
    public FuncionarioResponseDTO criar(FuncionarioRequestDTO dto) {
        if (funcionarioRepository.existsByCpfAndAtivoTrue(dto.cpf())) {
            throw new BusinessException("Já existe um funcionário com o CPF: " + dto.cpf());
        }
        if (funcionarioRepository.existsByMatriculaAndAtivoTrue(dto.matricula())) {
            throw new BusinessException("Já existe um funcionário com a matrícula: " + dto.matricula());
        }

        Funcionario funcionario = funcionarioMapper.toEntity(dto);
        return funcionarioMapper.toResponseDTO(funcionarioRepository.save(funcionario));
    }

    @Transactional
    public FuncionarioResponseDTO atualizar(Long id, FuncionarioRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .filter(Funcionario::getAtivo)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + id));

        if (!funcionario.getCpf().equals(dto.cpf()) && funcionarioRepository.existsByCpfAndAtivoTrue(dto.cpf())) {
            throw new BusinessException("Já existe um funcionário com o CPF: " + dto.cpf());
        }
        if (!funcionario.getMatricula().equals(dto.matricula()) && funcionarioRepository.existsByMatriculaAndAtivoTrue(dto.matricula())) {
            throw new BusinessException("Já existe um funcionário com a matrícula: " + dto.matricula());
        }

        funcionarioMapper.updateEntityFromDTO(dto, funcionario);
        return funcionarioMapper.toResponseDTO(funcionarioRepository.save(funcionario));
    }

    @Transactional
    public void deletar(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .filter(Funcionario::getAtivo)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + id));
        funcionario.setAtivo(false);
        funcionarioRepository.save(funcionario);
    }
}