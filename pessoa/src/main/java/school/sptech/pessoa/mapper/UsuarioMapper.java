package school.sptech.pessoa.mapper;

import school.sptech.pessoa.dto.TokenResponseDTO;
import school.sptech.pessoa.dto.UsuarioDto;
import school.sptech.pessoa.model.Usuario;

public class UsuarioMapper {
    public static UsuarioDto toDto(Usuario usuario){
        if (usuario == null){
            return null;
        }

        return new UsuarioDto(
                usuario.getLogin(),
                usuario.getSenha()
        );
    }

    public static TokenResponseDTO toToken(String texto){
        return new TokenResponseDTO(
                texto
        );
    }
}
