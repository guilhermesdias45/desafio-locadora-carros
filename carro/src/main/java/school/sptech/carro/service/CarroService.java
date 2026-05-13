package school.sptech.carro.service;

import org.springframework.stereotype.Service;
import school.sptech.carro.model.Carro;
import school.sptech.carro.repository.CarroRepository;

@Service
public class CarroService {

    private final CarroRepository repository;
    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public Carro save(Carro carro) {
        return repository.save(carro);
    }
}
