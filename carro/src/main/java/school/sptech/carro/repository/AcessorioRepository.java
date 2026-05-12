package school.sptech.carro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.carro.model.Acessorio;

public interface AcessorioRepository extends JpaRepository<Acessorio, Long> {
}
