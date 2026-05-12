package school.sptech.aluguel.aluguel;

import org.springframework.stereotype.Service;

@Service
public class AluguelService {
    private final AluguelRepository repository;

    public AluguelService(AluguelRepository repository) {
        this.repository = repository;
    }
}
