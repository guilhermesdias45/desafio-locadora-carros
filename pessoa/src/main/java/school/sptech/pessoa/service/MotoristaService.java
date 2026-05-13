package school.sptech.pessoa.service;

import com.projeto.model.Motorista;
import org.springframework.stereotype.Service;
import school.sptech.pessoa.dto.MotoristaRequestDTO;
import school.sptech.pessoa.dto.MotoristaResponseDTO;
import school.sptech.pessoa.repository.MotoristaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoristaService extends Motorista {

    private final MotoristaRepository motoristaRepository;

    public MotoristaService(MotoristaRepository motoristaRepository) {
        this.motoristaRepository = motoristaRepository;
    }

    public MotoristaService() {
        this.motoristaRepository = null;
    }


    public List<MotoristaResponseDTO> listarTodos() {
        return motoristaRepository.findAll()
                .stream()
                .map(MotoristaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public MotoristaResponseDTO buscarPorId(Long id) {
        MotoristaService motorista = (MotoristaService) motoristaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado com id: " + id));
        return MotoristaResponseDTO.fromEntity(motorista);
    }

    public MotoristaResponseDTO buscarPorCpf(String cpf) {
        MotoristaService motorista = (MotoristaService) motoristaRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado com CPF: " + cpf));
        return MotoristaResponseDTO.fromEntity(motorista);
    }

    public MotoristaResponseDTO criar(MotoristaRequestDTO dto) {
        if (motoristaRepository.existsByCpf(dto.cpf())) {
            throw new RuntimeException("Já existe um motorista com o CPF: " + dto.cpf());
        }
        if (motoristaRepository.existsByNumeroCNH(dto.numeroCNH())) {
            throw new RuntimeException("Já existe um motorista com a CNH: " + dto.numeroCNH());
        }

        MotoristaService motorista = new MotoristaService();
        motorista.setNome(dto.nome());
        motorista.setDataNascimento(dto.dataNascimento());
        motorista.setCpf(dto.cpf());
        motorista.setSexo(dto.sexo());
        motorista.setNumeroCNH(dto.numeroCNH());

        return MotoristaResponseDTO.fromEntity(motoristaRepository.save(motorista));
    }

    public MotoristaResponseDTO atualizar(Long id, MotoristaRequestDTO dto) {
        MotoristaService motorista = (MotoristaService) motoristaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado com id: " + id));

        if (!motorista.getCpf().equals(dto.cpf()) && motoristaRepository.existsByCpf(dto.cpf())) {
            throw new RuntimeException("Já existe um motorista com o CPF: " + dto.cpf());
        }

        if (!motorista.getNumeroCNH().equals(dto.numeroCNH()) && motoristaRepository.existsByNumeroCNH(dto.numeroCNH())) {
            throw new RuntimeException("Já existe um motorista com a CNH: " + dto.numeroCNH());
        }

        motorista.setNome(dto.nome());
        motorista.setDataNascimento(dto.dataNascimento());
        motorista.setCpf(dto.cpf());
        motorista.setSexo(dto.sexo());
        motorista.setNumeroCNH(dto.numeroCNH());

        return MotoristaResponseDTO.fromEntity(motoristaRepository.save(motorista));
    }

    public void deletar(Long id) {
        if (!motoristaRepository.existsById(id)) {
            throw new RuntimeException("Motorista não encontrado com id: " + id);
        }
        motoristaRepository.deleteById(id);
    }
}