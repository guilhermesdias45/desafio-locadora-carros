package school.sptech.aluguel.repository;

import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.aluguel.model.Aluguel;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    List<Aluguel> findAllByMotoristaId(Long id);

    List<Aluguel> findAllByCarroId(Long id);

    boolean existsByCarroIdAndDataEntregaLessThanEqualAndDataDevolucaoGreaterThanEqual(@Positive Long aLong, LocalDate dataDevolucao, LocalDate dataEntrega);
}