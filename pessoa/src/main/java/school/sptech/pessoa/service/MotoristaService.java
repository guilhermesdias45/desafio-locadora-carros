package school.sptech.pessoa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.pessoa.dto.MotoristaRequestDTO;
import school.sptech.pessoa.dto.MotoristaResponseDTO;
import school.sptech.pessoa.exception.BusinessException;
import school.sptech.pessoa.exception.ResourceNotFoundException;
import school.sptech.pessoa.mapper.MotoristaMapper;
import school.sptech.pessoa.model.Motorista;
import school.sptech.pessoa.model.Usuario;
import school.sptech.pessoa.repository.MotoristaRepository;
import school.sptech.pessoa.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MotoristaService {

    private final MotoristaRepository motoristaRepository;
    private final MotoristaMapper motoristaMapper;
    private final EmailProducerService emailProducerService;
    private final UsuarioRepository usuarioRepository;

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

    public MotoristaResponseDTO buscarPorEmail(String email) {
        Motorista motorista = motoristaRepository.findByEmailAndAtivoTrue(email)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com email: " + email));
        return motoristaMapper.toResponseDTO(motorista);
    }

    @Transactional
    public MotoristaResponseDTO criar(MotoristaRequestDTO dto) {
        if (dto.pessoa().dataNascimento().isAfter(LocalDate.now().minusYears(18))) {
            throw new BusinessException("Motorista deve ter 18 anos ou mais.");
        }
        if (motoristaRepository.existsByCpfAndAtivoTrue(dto.pessoa().cpf())) {
            throw new BusinessException("Já existe um motorista com o CPF: " + dto.pessoa().cpf());
        }
        if (motoristaRepository.existsByNumeroCNHAndAtivoTrue(dto.numeroCNH())) {
            throw new BusinessException("Já existe um motorista com a CNH: " + dto.numeroCNH());
        }
        if (motoristaRepository.existsByEmailAndAtivoTrue(dto.pessoa().email())) {
            throw new BusinessException("Já existe um motorista com o email: " + dto.pessoa().email());
        }

        Motorista motoristaSalvo = motoristaRepository.save(motoristaMapper.toEntity(dto));

        String loginAutenticado = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Usuario usuarioAutenticado = usuarioRepository.findByLogin(loginAutenticado)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário autenticado não encontrado."));

        try {
            emailProducerService.enviarDadosMotorista(motoristaSalvo, usuarioAutenticado);
        } catch (Exception e) {
            log.warn("Erro ao enfileirar dados do motorista: {}", e.getMessage());
        }

        return motoristaMapper.toResponseDTO(motoristaSalvo);
    }

    @Transactional
    public MotoristaResponseDTO atualizar(Long id, MotoristaRequestDTO dto) {
        Motorista motorista = motoristaRepository.findById(id)
                .filter(Motorista::getAtivo)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com id: " + id));

        if (dto.pessoa().dataNascimento().isAfter(LocalDate.now().minusYears(18))) {
            throw new BusinessException("Motorista deve ter 18 anos ou mais.");
        }
        if (!motorista.getCpf().equals(dto.pessoa().cpf()) && motoristaRepository.existsByCpfAndAtivoTrue(dto.pessoa().cpf())) {
            throw new BusinessException("Já existe um motorista com o CPF: " + dto.pessoa().cpf());
        }
        if (!motorista.getNumeroCNH().equals(dto.numeroCNH()) && motoristaRepository.existsByNumeroCNHAndAtivoTrue(dto.numeroCNH())) {
            throw new BusinessException("Já existe um motorista com a CNH: " + dto.numeroCNH());
        }
        if (!motorista.getEmail().equals(dto.pessoa().email()) && motoristaRepository.existsByEmailAndAtivoTrue(dto.pessoa().email())) {
            throw new BusinessException("Já existe um motorista com o email: " + dto.pessoa().email());
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