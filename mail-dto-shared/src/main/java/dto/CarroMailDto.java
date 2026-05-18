package dto;

import java.math.BigDecimal;
import java.util.List;

public record CarroMailDto(
        String placa,
        String chassi,
        String cor,
        BigDecimal valorDiaria,

        ModeloCarroDto modeloCarro,
        List<Acessorio>acessorios
) {
    public record ModeloCarroDto(
            String descricao,
            Categoria categoria,

            Fabricante fabricante
    ) {
    }

    public enum Categoria {
        HATCH_COMPACTO,
        HATCH_MEDIO,
        SEDAN_COMPACTO,
        SEDAN_MEDIO,
        SEDAN_GRANDE,
        MINIVAN,
        ESPORTIVO,
        UTILITARIO_COMERCIAL
    }

    public record Fabricante(
            String nome
    ) {
    }

    public record Acessorio(
            String descricao
    ) {
    }
}
