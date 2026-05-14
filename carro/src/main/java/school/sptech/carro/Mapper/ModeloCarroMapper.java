package school.sptech.carro.Mapper;

import school.sptech.carro.dto.modeloCarro.ModeloCarroRequest;
import school.sptech.carro.dto.modeloCarro.ModeloCarroResponse;
import school.sptech.carro.model.Fabricante;
import school.sptech.carro.model.ModeloCarro;

import java.util.List;

public class ModeloCarroMapper {

    public static ModeloCarro toEntity(ModeloCarroRequest request) {
        ModeloCarro modelo = new ModeloCarro();

        modelo.setDescricao(request.descricao());
        modelo.setCategoria(request.categoria());

        modelo.setFabricante(new Fabricante());
        modelo.getFabricante().setId(request.fabricanteId());

        return modelo;
    }

    public static ModeloCarroResponse toResponse(ModeloCarro modeloCarro) {
        return new ModeloCarroResponse(
                modeloCarro.getId(),
                modeloCarro.getDescricao(),
                modeloCarro.getCategoria(),
                new ModeloCarroResponse.FabricanteResponse(
                        modeloCarro.getFabricante().getId(),
                        modeloCarro.getFabricante().getNome()
                )
        );
    }

    public static List<ModeloCarroResponse> toResponse(List<ModeloCarro> modelos) {
        return modelos.stream()
                .map(ModeloCarroMapper::toResponse)
                .toList();
    }
}
