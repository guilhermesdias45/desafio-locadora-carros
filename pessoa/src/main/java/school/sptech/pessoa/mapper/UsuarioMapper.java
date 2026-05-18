package school.sptech.pessoa.mapper;

import school.sptech.pessoa.dto.TokenResponseDTO;
import school.sptech.pessoa.dto.UsuarioRequestDTO;
import school.sptech.pessoa.dto.UsuarioResponseDTO;
import school.sptech.pessoa.model.Usuario;

public class UsuarioMapper {
    public static UsuarioResponseDTO toDto(Usuario usuario){
        if (usuario == null){
            return null;
        }

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin()
        );
    }

    public static TokenResponseDTO toToken(String texto){
        return new TokenResponseDTO(
                texto
        );
    }
}
