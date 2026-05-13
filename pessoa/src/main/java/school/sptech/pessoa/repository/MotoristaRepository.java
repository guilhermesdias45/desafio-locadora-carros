package school.sptech.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.pessoa.model.Motorista;

import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    Optional<Motorista> findByCpf(String cpf);

    Optional<Motorista> findByNumeroCNH(String numeroCNH);

    boolean existsByCpf(String cpf);

    boolean existsByNumeroCNH(String numeroCNH);
}
