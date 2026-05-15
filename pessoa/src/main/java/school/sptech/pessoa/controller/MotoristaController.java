package school.sptech.pessoa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.pessoa.dto.MotoristaRequestDTO;
import school.sptech.pessoa.dto.MotoristaResponseDTO;
import school.sptech.pessoa.service.MotoristaService;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
@RequiredArgsConstructor
@Tag(name = "Motoristas", description = "Gerenciamento de motoristas da locadora")
public class MotoristaController {

    private final MotoristaService motoristaService;

    @Operation(summary = "Lista todos os motoristas ativos com paginação")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<MotoristaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(motoristaService.listarTodos());
    }

    @Operation(summary = "Busca motorista por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Motorista encontrado"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MotoristaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoristaService.buscarPorId(id));
    }

    @Operation(summary = "Busca motorista por CPF")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Motorista encontrado"),
            @ApiResponse(responseCode = "404", description = "CPF não cadastrado")
    })
    @GetMapping("/buscar")
    public ResponseEntity<MotoristaResponseDTO> buscarPorCpf(@RequestHeader("CPF") String cpf) {
        return ResponseEntity.ok(motoristaService.buscarPorCpf(cpf));
    }

    @Operation(summary = "Cadastra um novo motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Motorista criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "CPF ou CNH já cadastrado")
    })
    @PostMapping
    public ResponseEntity<MotoristaResponseDTO> criar(@Valid @RequestBody MotoristaRequestDTO dto) {
        MotoristaResponseDTO criado = motoristaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @Operation(summary = "Atualiza dados de um motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Motorista atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado"),
            @ApiResponse(responseCode = "409", description = "CPF ou CNH já em uso por outro motorista")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MotoristaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody MotoristaRequestDTO dto) {
        return ResponseEntity.ok(motoristaService.atualizar(id, dto));
    }

    @Operation(summary = "Desativa um motorista (soft delete)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Motorista desativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        motoristaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}