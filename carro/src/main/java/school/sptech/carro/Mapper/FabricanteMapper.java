package school.sptech.carro.Mapper;

import school.sptech.carro.dto.fabricante.FabricanteRequest;
import school.sptech.carro.model.Fabricante;

public class FabricanteMapper {

    public static Fabricante toEntity(FabricanteRequest request) {
        Fabricante fabricante = new Fabricante();

        fabricante.setNome(request.nome());

        return fabricante;
    }
}
