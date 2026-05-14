package school.sptech.pessoa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.pessoa.model.Funcionario;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Page<Funcionario> findAllByAtivoTrue(Pageable pageable);

    Optional<Funcionario> findByCpfAndAtivoTrue(String cpf);

    Optional<Funcionario> findByMatriculaAndAtivoTrue(String matricula);

    boolean existsByCpfAndAtivoTrue(String cpf);

    boolean existsByMatriculaAndAtivoTrue(String matricula);
}