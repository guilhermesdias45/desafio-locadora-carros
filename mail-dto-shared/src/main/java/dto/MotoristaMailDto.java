package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MotoristaMailDto(

        @JsonProperty("motorista")
        Usuario usuario,
        String matricula,
        Boolean funcionario
) {
    public record Usuario(
            String nome,
            String email
    ) {
    }
}
