package school.sptech.aluguel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import school.sptech.aluguel.dto.AluguelCompletoRequestDTO;
import school.sptech.aluguel.dto.AluguelRequestDTO;
import school.sptech.aluguel.exception.EntidadeConflitoException;
import school.sptech.aluguel.exception.EntidadeNaoEncontradaException;
import school.sptech.aluguel.mapper.AluguelMapper;
import school.sptech.aluguel.model.Aluguel;
import school.sptech.aluguel.repository.AluguelRepository;
import school.sptech.aluguel.repository.ApoliceRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AluguelService {
    private final AluguelRepository repository;

    private final ApoliceRepository apoliceRepository;

    public final WebClient motoristaWebClient;

    public final WebClient carroWebClient;

    private final EmailProducerService emailProducerService;

    public AluguelService(AluguelRepository repository, ApoliceRepository apoliceRepository, WebClient.Builder webClientBuilder, EmailProducerService emailProducerService) {
        this.repository = repository;
        this.apoliceRepository = apoliceRepository;
        this.motoristaWebClient = webClientBuilder.clone().baseUrl("http://localhost:8081").build();
        this.carroWebClient = webClientBuilder.clone().baseUrl("http://localhost:8080").build();
        this.emailProducerService = emailProducerService;
    }

    public List<Aluguel> listarTodos(){
        return repository.findAll();
    }

    public Aluguel salvar(AluguelRequestDTO dto){
        jakarta.servlet.http.HttpServletRequest currentRequest =
                ((org.springframework.web.context.request.ServletRequestAttributes)
                        org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes()).getRequest();
        String tokenOriginal = currentRequest.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);

        Aluguel aluguel = AluguelMapper.toEntity(dto);

        AluguelCompletoRequestDTO.MotoristaRequestDTO motorista = motoristaWebClient.get()
                .uri("/motoristas/{id}", aluguel.getMotoristaId())
                .retrieve()
                .bodyToMono(AluguelCompletoRequestDTO.MotoristaRequestDTO.class)
                .contextWrite(context -> context.put("AUTH_TOKEN", tokenOriginal))
                .block();

        if (motorista == null) {
            throw new EntidadeNaoEncontradaException("Motorista não encontrado no microsserviço Pessoa");
        }

        if (repository.existsByCarroIdAndDataEntregaLessThanEqualAndDataDevolucaoGreaterThanEqual(dto.carroId(), aluguel.getDataDevolucao(), aluguel.getDataEntrega())){
            throw new EntidadeConflitoException("Carro escolhido já está alugado nesse período, escolha outro período ou outro carro.");
        }

        AluguelCompletoRequestDTO.CarroRequestDTO carro = carroWebClient.get()
                .uri("/carros/{id}", aluguel.getCarroId())
                .retrieve()
                .bodyToMono(AluguelCompletoRequestDTO.CarroRequestDTO.class)
                .contextWrite(context -> context.put("AUTH_TOKEN", tokenOriginal))
                .block();

        if (carro == null) {
            throw new EntidadeNaoEncontradaException("Carro não encontrado no microsserviço Carro");
        }

        long dias = java.time.temporal.ChronoUnit.DAYS.between(aluguel.getDataEntrega(), aluguel.getDataDevolucao());
        BigDecimal valorTotal = carro.valorDiaria().multiply(BigDecimal.valueOf(dias));

        aluguel.setValorTotal(aluguel.getApolice().getValorFranquia().add(valorTotal));
        aluguel.setDataPedido(LocalDate.now());

        apoliceRepository.save(aluguel.getApolice());
        Aluguel aluguelRetorno = repository.save(aluguel);

        AluguelCompletoRequestDTO aluguelSalvo = AluguelMapper
                .toAluguelCompletoDto(
                        aluguelRetorno,
                        motorista,
                        carro
                );

        try {
            emailProducerService.enviarDadosAluguel(aluguelSalvo);
        } catch (Exception e) {
            log.warn("Erro ao enfileirar email: {}", e.getMessage());
        }

        return aluguelRetorno;
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
