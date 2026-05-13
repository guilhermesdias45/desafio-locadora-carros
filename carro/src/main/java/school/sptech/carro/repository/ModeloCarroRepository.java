package school.sptech.carro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.carro.model.ModeloCarro;

public interface ModeloCarroRepository extends JpaRepository<ModeloCarro, Long> {
}
