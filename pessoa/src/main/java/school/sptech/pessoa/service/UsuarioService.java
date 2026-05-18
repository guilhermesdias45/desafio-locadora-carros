package school.sptech.pessoa.service;

import lombok.RequiredArgsConstructor;
import school.sptech.pessoa.dto.TokenResponseDTO;
import school.sptech.pessoa.dto.UsuarioCreateDTO;
import school.sptech.pessoa.dto.UsuarioRequestDTO;
import school.sptech.pessoa.exception.BusinessException;
import school.sptech.pessoa.exception.ResourceNotFoundException;
import school.sptech.pessoa.mapper.UsuarioMapper;
import school.sptech.pessoa.model.Usuario;
import school.sptech.pessoa.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public Usuario salvar(UsuarioCreateDTO dto) {
        if (repository.findByLogin(dto.login()).isPresent()) {
            throw new RuntimeException("Credenciais já existentes.");
        }

        if (dto.dataNascimento().isAfter(LocalDate.now().minusYears(18))) {
            throw new BusinessException("É necessário ter 18 anos ou mais para se cadastrar.");
        }

        if (Boolean.TRUE.equals(dto.funcionario()) &&
                (dto.matricula() == null || dto.matricula().isBlank())) {
            throw new BusinessException("Matrícula é obrigatória para funcionários.");
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setFuncionario(dto.funcionario());
        usuario.setMatricula(dto.matricula());
        return repository.save(usuario);
    }

    public TokenResponseDTO autenticar(UsuarioRequestDTO dto) {
        Usuario user = repository.findByLogin(dto.login()).orElseThrow(
                () -> new ResourceNotFoundException("Login inexistente.")
        );
        String jwtToken = jwtService.gerarToken(user.getLogin());

        if (user != null && passwordEncoder.matches(dto.senha(), user.getSenha())) {
            return UsuarioMapper.toToken(jwtToken);
        }

        throw new RuntimeException("Credenciais inválidos.");
    }
}
