package school.sptech.carro.dto.carro;

import school.sptech.carro.model.Categoria;

import java.math.BigDecimal;
import java.util.List;

public record CarroResponse(
        Long id,
        String placa,
        String chassi,
        String cor,
        BigDecimal valorDiaria,
        ModeloCarroResponse modeloCarro,
        List<AcessorioResponse> acessorios
) {
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

    public record AcessorioResponse(
            Long id,
            String descricao
    ) {
    }
}

