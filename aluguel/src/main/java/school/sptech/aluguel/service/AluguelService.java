package school.sptech.aluguel.service;

import org.springframework.stereotype.Service;
import school.sptech.aluguel.dto.AluguelRequestDTO;
import school.sptech.aluguel.exception.NotFoundException;
import school.sptech.aluguel.mapper.AluguelMapper;
import school.sptech.aluguel.model.Aluguel;
import school.sptech.aluguel.repository.AluguelRepository;

import java.math.BigDecimal;

@Service
public class AluguelService {
    private final AluguelRepository repository;

    public AluguelService(AluguelRepository repository) {
        this.repository = repository;
    }

    public Aluguel salvar(AluguelRequestDTO dto){
        Aluguel aluguel = AluguelMapper.toEntity(dto);
        aluguel.setValorTotal(
                aluguel.getApolice().getValorFranquia().add(
                        aluguel.getCarro().getValorDiaria().multiply(
                                BigDecimal.valueOf(aluguel.getDataEntrega().compareTo(aluguel.getDataDevolucao()))
                        )
                )
        );

        return repository.save(aluguel);
    }

    public Aluguel buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Aluguel não encontrado"));
    }
}
