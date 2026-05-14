package school.sptech.aluguel.mapper;

import school.sptech.aluguel.dto.AluguelRequestDTO;
import school.sptech.aluguel.dto.AluguelResponseDTO;
import school.sptech.aluguel.model.Aluguel;

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
                dto.motoristaId(),
                dto.carroId()
        );

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
                        aluguel.getApolice().getId(),
                        aluguel.getApolice().getValorFranquia(),
                        aluguel.getApolice().getProtecaoTerceiro(),
                        aluguel.getApolice().getProtecaoCausasNaturais(),
                        aluguel.getApolice().getProtecaoRoubo()
                ),
                aluguel.getMotoristaId(),
                aluguel.getCarroId()
        );
    }

    public static List<AluguelResponseDTO> toDto(List<Aluguel> alugueis){
        return alugueis.stream().map(AluguelMapper::toDto).toList();
    }
}