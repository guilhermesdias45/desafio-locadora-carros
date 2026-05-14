package school.sptech.carro.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import school.sptech.carro.exception.EntidadeConflitoException;
import school.sptech.carro.exception.EntidadeInvalidaException;
import school.sptech.carro.model.Acessorio;
import school.sptech.carro.repository.AcessorioRepository;

import java.util.List;

@Service
public class AcessorioService {

    private final AcessorioRepository acessorioRepository;
    public AcessorioService(AcessorioRepository acessorioRepository) {
        this.acessorioRepository = acessorioRepository;
    }

    @Transactional
    public Acessorio save(Acessorio acessorio) {
        if (acessorio == null) { throw new EntidadeInvalidaException("Acessório não pode ser nulo"); }
        if (acessorioRepository.existsByDescricao(acessorio.getDescricao())) { throw new EntidadeConflitoException("Acessório já cadastrado"); }

        return acessorioRepository.save(acessorio);
    }

    @Transactional(readOnly = true)
    public List<Acessorio> findAll() {
        return acessorioRepository.findAll();
    }
}
