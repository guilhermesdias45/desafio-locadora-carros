package school.sptech.pessoa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.pessoa.dto.MotoristaRequestDTO;
import school.sptech.pessoa.dto.MotoristaResponseDTO;
import school.sptech.pessoa.exception.BusinessException;
import school.sptech.pessoa.exception.ResourceNotFoundException;
import school.sptech.pessoa.mapper.MotoristaMapper;
import school.sptech.pessoa.model.Motorista;
import school.sptech.pessoa.repository.MotoristaRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MotoristaService {

    private final MotoristaRepository motoristaRepository;
    private final MotoristaMapper motoristaMapper;

    public List<MotoristaResponseDTO> listarTodos() {
        return motoristaRepository.findAllByAtivoTrue()
                .stream()
                .map(motoristaMapper::toResponseDTO)
                .toList();
    }

    public MotoristaResponseDTO buscarPorId(Long id) {
        Motorista motorista = motoristaRepository.findById(id)
                .filter(Motorista::getAtivo)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com id: " + id));
        return motoristaMapper.toResponseDTO(motorista);
    }

    public MotoristaResponseDTO buscarPorCpf(String cpf) {
        Motorista motorista = motoristaRepository.findByCpfAndAtivoTrue(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com CPF: " + cpf));
        return motoristaMapper.toResponseDTO(motorista);
    }

    @Transactional
    public MotoristaResponseDTO criar(MotoristaRequestDTO dto) {
        if (motoristaRepository.existsByCpfAndAtivoTrue(dto.cpf())) {
            throw new BusinessException("Já existe um motorista com o CPF: " + dto.cpf());
        }
        if (motoristaRepository.existsByNumeroCNHAndAtivoTrue(dto.numeroCNH())) {
            throw new BusinessException("Já existe um motorista com a CNH: " + dto.numeroCNH());
        }

        Motorista motorista = motoristaMapper.toEntity(dto);
        return motoristaMapper.toResponseDTO(motoristaRepository.save(motorista));
    }

    @Transactional
    public MotoristaResponseDTO atualizar(Long id, MotoristaRequestDTO dto) {
        Motorista motorista = motoristaRepository.findById(id)
                .filter(Motorista::getAtivo)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com id: " + id));

        if (!motorista.getCpf().equals(dto.cpf()) && motoristaRepository.existsByCpfAndAtivoTrue(dto.cpf())) {
            throw new BusinessException("Já existe um motorista com o CPF: " + dto.cpf());
        }
        if (!motorista.getNumeroCNH().equals(dto.numeroCNH()) && motoristaRepository.existsByNumeroCNHAndAtivoTrue(dto.numeroCNH())) {
            throw new BusinessException("Já existe um motorista com a CNH: " + dto.numeroCNH());
        }

        motoristaMapper.updateEntityFromDTO(dto, motorista);
        return motoristaMapper.toResponseDTO(motoristaRepository.save(motorista));
    }

    @Transactional
    public void deletar(Long id) {
        Motorista motorista = motoristaRepository.findById(id)
                .filter(Motorista::getAtivo)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com id: " + id));
        motorista.setAtivo(false);
        motoristaRepository.save(motorista);
    }
}