package school.sptech.aluguel.mapper;

import com.projeto.model.Motorista;
import school.sptech.aluguel.dto.AluguelRequestDTO;
import school.sptech.aluguel.dto.AluguelResponseDTO;
import school.sptech.aluguel.model.Aluguel;
import school.sptech.carro.model.Carro;
import school.sptech.pessoa.dto.MotoristaResponseDTO;

import java.util.List;

public class AluguelMapper {
    public static Aluguel toEntity(AluguelRequestDTO dto){
        if (dto == null){
            return null;
        }

        Aluguel aluguel = new Aluguel(
                dto.dataEntrega(),
                dto.dataDevolucao(),
                dto.valorTotal(),
                ApoliceMapper.toEntity(dto.apolice()),
                new Motorista(),
                new Carro()
        );
        aluguel.getMotorista().setId(dto.motoristaId());
        aluguel.getCarro().setId(dto.carroId());

        return aluguel;
    }

    public static List<Aluguel> toEntity(List<AluguelRequestDTO> dto){
        return dto.stream().map(AluguelMapper::toEntity).toList();
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
                new AluguelResponseDTO.ApoliceSeguroResponse(
                        aluguel.getApolice().getValorFranquia(),
                        aluguel.getApolice().getProtecaoTerceiro(),
                        aluguel.getApolice().getProtecaoCausasNaturais(),
                        aluguel.getApolice().getProtecaoRoubo()
                ),
                new MotoristaResponseDTO(
                        aluguel.getMotorista().getId(),
                        aluguel.getMotorista().getNome(),
                        aluguel.getMotorista().getDataNascimento(),
                        aluguel.getMotorista().getCpf(),
                        aluguel.getMotorista().getSexo(),
                        aluguel.getMotorista().getNumeroCNH()
                ),
                new CarroResponseDTO(
                        aluguel.getCarro().getId(),
                        aluguel.getCarro().getPlaca(),
                        aluguel.getCarro().getChassi(),
                        aluguel.getCarro().getCor(),
                        aluguel.getCarro().getValorDiaria(),
                        aluguel.getCarro().getModeloCarro(),
                        aluguel.getCarro().getAcessorios()
                )
        );
    }

    public static List<AluguelResponseDTO> toDto(List<Aluguel> alugueis){
        return alugueis.stream().map(AluguelMapper::toDto).toList();
    }
}