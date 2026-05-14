package school.sptech.carro.Mapper;

import school.sptech.carro.dto.fabricante.FabricanteRequest;
import school.sptech.carro.dto.fabricante.FabricanteResponse;
import school.sptech.carro.model.Fabricante;

import java.util.List;

public class FabricanteMapper {

    public static Fabricante toEntity(FabricanteRequest request) {
        Fabricante fabricante = new Fabricante();

        fabricante.setNome(request.nome());

        return fabricante;
    }

    public static FabricanteResponse toResponse(Fabricante fabricante) {
        return new FabricanteResponse(
                fabricante.getId(),
                fabricante.getNome()
        );
    }

    public static List<FabricanteResponse> toResponse(List<Fabricante> fabricantes) {
        return fabricantes.stream()
                .map(FabricanteMapper::toResponse)
                .toList();
    }
}
