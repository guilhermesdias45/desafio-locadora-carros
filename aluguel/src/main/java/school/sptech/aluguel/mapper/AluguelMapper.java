package school.sptech.aluguel.mapper;

import com.projeto.model.Motorista;
import school.sptech.aluguel.dto.AluguelRequestDTO;
import school.sptech.aluguel.dto.AluguelResponseDTO;
import school.sptech.aluguel.model.Aluguel;
import school.sptech.carro.model.Carro;
import school.sptech.pessoa.dto.MotoristaResponseDTO;

public class AluguelMapper {
    public static Aluguel toEntity(AluguelRequestDTO dto){
        if (dto == null){
            return null;
        }

        Aluguel aluguel = new Aluguel(
                dto.dataEntrega(),
                dto.dataDevolucao(),
                dto.valorTotal(),
                dto.apolice(),
                new Motorista(),
                new Carro()
        );
        aluguel.getMotorista().setId(dto.motoristaId());
        aluguel.getCarro().setId(dto.carroId());

        return aluguel;
    }

    public static AluguelResponseDTO toDto(Aluguel aluguel){
        if (aluguel == null){
            return null;
        }

        return new AluguelResponseDTO(
                aluguel.getId(),
                aluguel.getDataPedido(),
                aluguel.getDataEntrega(),
                aluguel.getDataDevolucao(),
                aluguel.getValorTotal(),
                aluguel.getApolice(),
                new MotoristaResponseDTO(
                        aluguel.getMotorista().getId(),
                        aluguel.getMotorista().getNome(),
                        aluguel.getMotorista().getDataNascimento(),
                        aluguel.getMotorista().getCpf(),
                        aluguel.getMotorista().getSexo(),
                        aluguel.getMotorista().getNumeroCNH()
                ),
                new CarroResponseDTO()
        );
    }
}