package school.sptech.carro.dto.modeloCarro;

import school.sptech.carro.model.Categoria;

public record ModeloCarroResponse(
        Long id,
        String descricao,
        Categoria categoria,
        FabricanteResponse fabricante
) {
    public record FabricanteResponse(
            Long id,
            String nome
    ) {
    }
}

