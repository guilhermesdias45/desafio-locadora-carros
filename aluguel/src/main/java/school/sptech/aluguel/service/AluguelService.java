package school.sptech.aluguel.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import school.sptech.aluguel.dto.AluguelRequestDTO;
import school.sptech.aluguel.dto.CarroRequestDTO;
import school.sptech.aluguel.dto.MotoristaRequestDTO;
import school.sptech.aluguel.exception.EntidadeNaoEncontradaException;
import school.sptech.aluguel.mapper.AluguelMapper;
import school.sptech.aluguel.model.Aluguel;
import school.sptech.aluguel.repository.AluguelRepository;
import school.sptech.aluguel.repository.ApoliceRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AluguelService {
    private final AluguelRepository repository;

    private final ApoliceRepository apoliceRepository;

    public final WebClient motoristaWebClient;

    public final WebClient carroWebClient;

    public AluguelService(AluguelRepository repository, ApoliceRepository apoliceRepository, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.apoliceRepository = apoliceRepository;
        this.motoristaWebClient = webClientBuilder.baseUrl("http://localhost:8081").build();
        this.carroWebClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public List<Aluguel> listarTodos(){
        return repository.findAll();
    }

    public Aluguel salvar(AluguelRequestDTO dto, String token){
        Aluguel aluguel = AluguelMapper.toEntity(dto);

        MotoristaRequestDTO motorista = motoristaWebClient.get()
                .uri("/motoristas/{id}", aluguel.getMotoristaId()).header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(MotoristaRequestDTO.class)
                .block();

        if (motorista == null) {
            throw new EntidadeNaoEncontradaException("Motorista não encontrado no microsserviço Pessoa");
        }

        CarroRequestDTO carro = carroWebClient.get()
                .uri("/carros/{id}", aluguel.getCarroId())
                .retrieve()
                .bodyToMono(CarroRequestDTO.class)
                .block();

        if (carro == null) {
            throw new EntidadeNaoEncontradaException("Carro não encontrado no microsserviço Carro");
        }

        long dias = java.time.temporal.ChronoUnit.DAYS.between(aluguel.getDataEntrega(), aluguel.getDataDevolucao());
        BigDecimal valorDiarias = carro.valorDiaria().multiply(BigDecimal.valueOf(dias));

        aluguel.setValorTotal(aluguel.getApolice().getValorFranquia().add(valorDiarias));
        aluguel.setDataPedido(LocalDateTime.now());

        apoliceRepository.save(aluguel.getApolice());
        return repository.save(aluguel);
    }

    public Aluguel buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Aluguel não encontrado"));
    }

    public List<Aluguel> buscarPorCliente(Long id){
        return repository.findAllByMotoristaId(id);
    }

    public List<Aluguel> buscarPorCarro(Long id){
        return repository.findAllByCarroId(id);
    }
}
