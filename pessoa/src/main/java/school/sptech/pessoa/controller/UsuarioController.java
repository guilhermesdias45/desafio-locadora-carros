package school.sptech.pessoa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.pessoa.dto.TokenResponseDTO;
import school.sptech.pessoa.dto.UsuarioCreateDTO;
import school.sptech.pessoa.dto.UsuarioRequestDTO;
import school.sptech.pessoa.dto.UsuarioResponseDTO;
import school.sptech.pessoa.mapper.UsuarioMapper;
import school.sptech.pessoa.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvar(@RequestBody @Valid UsuarioCreateDTO dto) {
        return ResponseEntity.status(201).body(UsuarioMapper.toDto(usuarioService.salvar(dto)));
    }

    @PostMapping("/autenticar")
    public ResponseEntity<TokenResponseDTO> autenticar(@RequestBody @Valid UsuarioRequestDTO dto) {
        return ResponseEntity.status(200).body(usuarioService.autenticar(dto));
    }
}