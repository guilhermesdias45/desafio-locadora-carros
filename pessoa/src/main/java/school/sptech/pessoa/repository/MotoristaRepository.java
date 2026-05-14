package school.sptech.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.pessoa.model.Motorista;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    List<Motorista> findAllByAtivoTrue();

    Optional<Motorista> findByCpfAndAtivoTrue(String cpf);

    Optional<Motorista> findByNumeroCNHAndAtivoTrue(String numeroCNH);

    boolean existsByCpfAndAtivoTrue(String cpf);

    boolean existsByNumeroCNHAndAtivoTrue(String numeroCNH);
}
