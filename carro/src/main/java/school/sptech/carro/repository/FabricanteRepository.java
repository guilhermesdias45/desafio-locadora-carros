package school.sptech.carro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.carro.model.Fabricante;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

    Boolean existsByNome(String nome);
}
