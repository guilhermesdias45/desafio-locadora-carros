package school.sptech.carro.service;

import org.springframework.stereotype.Service;
import school.sptech.carro.exception.EntidadeConflitoException;
import school.sptech.carro.exception.EntidadeInvalidaException;
import school.sptech.carro.exception.EntidadeNaoEncontradaException;
import school.sptech.carro.model.ModeloCarro;
import school.sptech.carro.repository.FabricanteRepository;
import school.sptech.carro.repository.ModeloCarroRepository;

import java.util.List;

@Service
public class ModeloCarroService {

    private final ModeloCarroRepository modeloCarroRepository;
    private final FabricanteRepository fabricanteRepository;
    public ModeloCarroService(ModeloCarroRepository modeloCarroRepository, FabricanteRepository fabricanteRepository) {
        this.modeloCarroRepository = modeloCarroRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    public ModeloCarro save(ModeloCarro modeloCarro) {
        if (modeloCarro == null) { throw new EntidadeInvalidaException("Modelo não pode ser nulo"); }

        var fabricante = fabricanteRepository.findById(modeloCarro.getFabricante().getId());
        if (fabricante.isEmpty()) { throw new EntidadeNaoEncontradaException("Fabricante não encontrado"); }

        if (modeloCarroRepository.existsByDescricaoAndCategoriaAndFabricante(
                modeloCarro.getDescricao(), modeloCarro.getCategoria(), modeloCarro.getFabricante()))
                { throw new EntidadeConflitoException("Acessório já cadastrado"); }

        var salvo = modeloCarroRepository.save(modeloCarro);
        salvo.setFabricante(fabricante.get());

        return salvo;
    }

    public List<ModeloCarro> findAll() {
        return modeloCarroRepository.findAll();
    }
}
