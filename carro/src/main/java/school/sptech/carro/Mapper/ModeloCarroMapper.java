package school.sptech.carro.Mapper;

import school.sptech.carro.dto.modeloCarro.ModeloCarroRequest;
import school.sptech.carro.model.Fabricante;
import school.sptech.carro.model.ModeloCarro;

public class ModeloCarroMapper {

    public static ModeloCarro toEntity(ModeloCarroRequest request) {
        ModeloCarro modelo = new ModeloCarro();

        modelo.setDescricao(request.descricao());
        modelo.setCategoria(request.categoria());

        modelo.setFabricante(new Fabricante());
        modelo.getFabricante().setId(request.fabricanteId());

        return modelo;
    }
}
