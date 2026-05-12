package school.sptech.carro.service;

import org.springframework.stereotype.Service;
import school.sptech.carro.model.ModeloCarro;
import school.sptech.carro.repository.ModeloCarroRepository;

@Service
public class ModeloCarroService {

    private final ModeloCarroRepository repository;
    public ModeloCarroService(ModeloCarroRepository repository) {
        this.repository = repository;
    }

    public ModeloCarro save(ModeloCarro modeloCarro) {
        return repository.save(modeloCarro);
    }
}
