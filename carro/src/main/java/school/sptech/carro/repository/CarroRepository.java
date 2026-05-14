package school.sptech.carro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.carro.model.Acessorio;
import school.sptech.carro.model.Carro;
import school.sptech.carro.model.ModeloCarro;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByModeloCarro(ModeloCarro modelo);

    List<Carro> findByAcessoriosContaining(Acessorio acessorio);

    Boolean existsByPlaca(String placa);
}
