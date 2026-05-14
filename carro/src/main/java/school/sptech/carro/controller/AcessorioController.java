package school.sptech.carro.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.carro.Mapper.AcessorioMapper;
import school.sptech.carro.dto.acessorio.AcessorioRequest;
import school.sptech.carro.dto.acessorio.AcessorioResponse;
import school.sptech.carro.model.Acessorio;
import school.sptech.carro.service.AcessorioService;

import java.util.List;

@RestController
@RequestMapping("/acessorios")
public class AcessorioController {

    private final AcessorioService acessorioService;

    public AcessorioController(AcessorioService acessorioService) {
        this.acessorioService = acessorioService;
    }

    @PostMapping
    public ResponseEntity<AcessorioResponse> salvarFabricante(@RequestBody @Valid AcessorioRequest acessorioRequest) {
        Acessorio salvo = acessorioService.save(AcessorioMapper.toEntity(acessorioRequest));
        return ResponseEntity.status(201).body(AcessorioMapper.toResponse(salvo));
    }

    @GetMapping
    public ResponseEntity<List<AcessorioResponse>> listarAcessorios() {
        List<Acessorio> acessorios = acessorioService.findAll();
        if (acessorios.isEmpty()) { return ResponseEntity.noContent().build(); }
        List<AcessorioResponse> response = AcessorioMapper.toResponse(acessorios);
        return ResponseEntity.ok(response);
    }
}
