package school.sptech.pessoa.service;

import lombok.RequiredArgsConstructor;
import school.sptech.pessoa.dto.TokenResponseDTO;
import school.sptech.pessoa.dto.UsuarioDto;
import school.sptech.pessoa.exception.ResourceNotFoundException;
import school.sptech.pessoa.mapper.UsuarioMapper;
import school.sptech.pessoa.model.Usuario;
import school.sptech.pessoa.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public Usuario salvar(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        return repository.save(usuario);
    }

    public TokenResponseDTO autenticar(UsuarioDto dto) {
        Usuario user = repository.findByLogin(dto.login()).orElseThrow(
                () -> new ResourceNotFoundException("Login inexistente.")
        );
        String jwtToken = jwtService.gerarToken(user.getLogin());

        if (user != null && passwordEncoder.matches(dto.senha(), user.getSenha())) {
            return UsuarioMapper.toToken(jwtToken);
        }

        throw new RuntimeException("Usuário ou senha inválidos");
    }
}
