package school.sptech.aluguel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.aluguel.model.Aluguel;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
}
