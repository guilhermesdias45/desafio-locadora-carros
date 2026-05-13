package school.sptech.carro.service;

import org.springframework.stereotype.Service;
import school.sptech.carro.model.Fabricante;
import school.sptech.carro.repository.FabricanteRepository;

@Service
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;
    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    public Fabricante save(Fabricante fabricante) {
        return fabricanteRepository.save(fabricante);
    }
}
