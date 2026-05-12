package school.sptech.aluguel.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import school.sptech.aluguel.dto.AluguelRequestDTO;
import school.sptech.aluguel.exception.NotFoundException;
import school.sptech.aluguel.mapper.AluguelMapper;
import school.sptech.aluguel.model.Aluguel;
import school.sptech.aluguel.repository.AluguelRepository;

import java.math.BigDecimal;

@Service
public class AluguelService {
    private final AluguelRepository repository;

    public WebClient motoristaWebClient;

    public WebClient carroWebClient;

    public AluguelService(AluguelRepository repository, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.motoristaWebClient = webClientBuilder.baseUrl("http://localhost:8080").build();
        this.carroWebClient = webClientBuilder.baseUrl("http://localhost:8082").build();
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
