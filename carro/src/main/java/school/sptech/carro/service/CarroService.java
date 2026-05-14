package school.sptech.carro.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.carro.exception.EntidadeConflitoException;
import school.sptech.carro.exception.EntidadeInvalidaException;
import school.sptech.carro.exception.EntidadeNaoEncontradaException;
import school.sptech.carro.model.Acessorio;
import school.sptech.carro.model.Carro;
import school.sptech.carro.model.ModeloCarro;
import school.sptech.carro.repository.AcessorioRepository;
import school.sptech.carro.repository.CarroRepository;
import school.sptech.carro.repository.FabricanteRepository;
import school.sptech.carro.repository.ModeloCarroRepository;

import java.util.List;

@Service
public class CarroService {

    private final CarroRepository carroRepository;
    private final ModeloCarroRepository modeloCarroRepository;
    private final AcessorioRepository acessorioRepository;
    private final FabricanteRepository fabricanteRepository;

    public CarroService(CarroRepository carroRepository, ModeloCarroRepository modeloCarroRepository, AcessorioRepository acessorioRepository, FabricanteRepository fabricanteRepository) {
        this.carroRepository = carroRepository;
        this.modeloCarroRepository = modeloCarroRepository;
        this.acessorioRepository = acessorioRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    public Carro save(Carro carro) {
        if (carro == null) { throw new EntidadeInvalidaException("Carro não pode ser nulo"); }
        if (carroRepository.existsByPlaca(carro.getPlaca())) { throw new EntidadeConflitoException("Placa já cadastrada"); }

        var modelo = modeloCarroRepository.findById(carro.getModeloCarro().getId());
        carro.setModeloCarro(modelo.get());

        var fabricante = fabricanteRepository.findById(carro.getModeloCarro().getFabricante().getId());
        carro.getModeloCarro().setFabricante(fabricante.get());

        carro.getAcessorios().forEach(a -> {
                    var acessorioSalvo = acessorioRepository.findById(a.getId());
                    a.setDescricao(acessorioSalvo.get().getDescricao());
                });

        return carroRepository.save(carro);
    }

    public List<Carro> findAll() {
        return carroRepository.findAll();
    }

    public List<Carro> findByModelo(Long modeloId) {
        ModeloCarro modelo = modeloCarroRepository.findById(modeloId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Modelo de carro não encontrado"));

        return carroRepository.findByModeloCarro(modelo);
    }

    public List<Carro> findByAcessorio(Long acessorioId) {
        Acessorio acessorio = acessorioRepository.findById(acessorioId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Acessório não encontrado"));

        return carroRepository.findByAcessoriosContaining(acessorio);
    }
}

