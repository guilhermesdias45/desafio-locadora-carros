package school.sptech.pessoa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.pessoa.dto.TokenResponseDTO;
import school.sptech.pessoa.dto.UsuarioDto;
import school.sptech.pessoa.mapper.UsuarioMapper;
import school.sptech.pessoa.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> salvar(@RequestBody @Valid UsuarioDto dto){
        return ResponseEntity.status(201).body(UsuarioMapper.toDto(usuarioService.salvar(dto)));
    }

    @PostMapping("/autenticar")
    public ResponseEntity<TokenResponseDTO> autenticar(@RequestBody @Valid UsuarioDto dto){
        return ResponseEntity.status(200).body(usuarioService.autenticar(dto));
    }
}
