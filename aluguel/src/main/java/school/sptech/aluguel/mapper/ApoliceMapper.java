package school.sptech.aluguel.mapper;

import school.sptech.aluguel.dto.AluguelRequestDTO;
import school.sptech.aluguel.model.ApoliceSeguro;

public class ApoliceMapper {
    public static ApoliceSeguro toEntity(AluguelRequestDTO.ApoliceSeguroRequest dto){
        if (dto == null){
            return null;
        }

        return new ApoliceSeguro(
                null,
                dto.valorFranquia(),
                dto.protecaoTerceiro(),
                dto.protecaoCausasNaturais(),
                dto.protecaoRoubo()
        );
    }
}