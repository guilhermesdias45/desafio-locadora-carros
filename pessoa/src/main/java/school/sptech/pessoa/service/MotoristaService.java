package school.sptech.pessoa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.pessoa.dto.MotoristaRequestDTO;
import school.sptech.pessoa.dto.MotoristaResponseDTO;
import school.sptech.pessoa.exception.BusinessException;
import school.sptech.pessoa.exception.ResourceNotFoundException;
import school.sptech.pessoa.mapper.MotoristaMapper;
import school.sptech.pessoa.model.Motorista;
import school.sptech.pessoa.repository.MotoristaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MotoristaService {

    private final MotoristaRepository motoristaRepository;
    private final MotoristaMapper motoristaMapper;

    public List<MotoristaResponseDTO> listarTodos() {
        return motoristaRepository.findAll()
                .stream()
                .map(motoristaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public MotoristaResponseDTO buscarPorId(Long id) {
        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com id: " + id));
        return motoristaMapper.toResponseDTO(motorista);
    }

    public MotoristaResponseDTO buscarPorCpf(String cpf) {
        Motorista motorista = motoristaRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com CPF: " + cpf));
        return motoristaMapper.toResponseDTO(motorista);
    }

    public MotoristaResponseDTO criar(MotoristaRequestDTO dto) {
        if (motoristaRepository.existsByCpf(dto.cpf())) {
            throw new BusinessException("Já existe um motorista com o CPF: " + dto.cpf());
        }
        if (motoristaRepository.existsByNumeroCNH(dto.numeroCNH())) {
            throw new BusinessException("Já existe um motorista com a CNH: " + dto.numeroCNH());
        }

        Motorista motorista = motoristaMapper.toEntity(dto);
        return motoristaMapper.toResponseDTO(motoristaRepository.save(motorista));
    }

    public MotoristaResponseDTO atualizar(Long id, MotoristaRequestDTO dto) {
        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com id: " + id));

        if (!motorista.getCpf().equals(dto.cpf()) && motoristaRepository.existsByCpf(dto.cpf())) {
            throw new BusinessException("Já existe um motorista com o CPF: " + dto.cpf());
        }
        if (!motorista.getNumeroCNH().equals(dto.numeroCNH()) && motoristaRepository.existsByNumeroCNH(dto.numeroCNH())) {
            throw new BusinessException("Já existe um motorista com a CNH: " + dto.numeroCNH());
        }

        motoristaMapper.updateEntityFromDTO(dto, motorista);
        return motoristaMapper.toResponseDTO(motoristaRepository.save(motorista));
    }

    public void deletar(Long id) {
        if (!motoristaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Motorista não encontrado com id: " + id);
        }
        motoristaRepository.deleteById(id);
    }
}