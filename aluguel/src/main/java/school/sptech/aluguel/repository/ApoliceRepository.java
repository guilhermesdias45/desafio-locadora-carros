package school.sptech.aluguel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.aluguel.model.ApoliceSeguro;

public interface ApoliceRepository extends JpaRepository<ApoliceSeguro, Long> {
}
