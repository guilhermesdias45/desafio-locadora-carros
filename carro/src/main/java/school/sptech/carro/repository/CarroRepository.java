package school.sptech.carro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.carro.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
