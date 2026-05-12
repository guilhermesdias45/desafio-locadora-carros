package school.sptech.pessoa.repository;

import com.projeto.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    Optional<Motorista> findByCpf(String cpf);

    Optional<Motorista> findByNumeroCNH(String numeroCNH);

    boolean existsByCpf(String cpf);

    boolean existsByNumeroCNH(String numeroCNH);
}
