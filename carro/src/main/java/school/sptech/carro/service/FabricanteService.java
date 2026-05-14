package school.sptech.carro.service;

import org.springframework.stereotype.Service;
import school.sptech.carro.exception.EntidadeConflitoException;
import school.sptech.carro.exception.EntidadeInvalidaException;
import school.sptech.carro.model.Fabricante;
import school.sptech.carro.repository.FabricanteRepository;

import java.util.List;

@Service
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;
    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    public Fabricante save(Fabricante fabricante) {
        if (fabricante == null) { throw new EntidadeInvalidaException("Fabricante não pode ser nulo"); }
        if (fabricanteRepository.existsByNome(fabricante.getNome())) { throw new EntidadeConflitoException("Fabricante já cadastrado"); }

        return fabricanteRepository.save(fabricante);
    }

    public List<Fabricante> findAll() {
        return fabricanteRepository.findAll();
    }
}
