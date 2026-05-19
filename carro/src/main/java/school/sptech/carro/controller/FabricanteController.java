package school.sptech.carro.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.carro.Mapper.FabricanteMapper;
import school.sptech.carro.dto.fabricante.FabricanteRequest;
import school.sptech.carro.dto.fabricante.FabricanteResponse;
import school.sptech.carro.model.Fabricante;
import school.sptech.carro.service.FabricanteService;

import java.util.List;

@RestController
@RequestMapping("/fabricantes")
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class FabricanteController {

    private final FabricanteService fabricanteService;

    public FabricanteController(FabricanteService fabricanteService) {
        this.fabricanteService = fabricanteService;
    }

    @PostMapping
    public ResponseEntity<FabricanteResponse> salvarFabricante(@RequestBody @Valid FabricanteRequest fabricanteRequest) {
        Fabricante salvo = fabricanteService.save(FabricanteMapper.toEntity(fabricanteRequest));
        return ResponseEntity.status(201).body(FabricanteMapper.toResponse(salvo));
    }

    @GetMapping
    public ResponseEntity<List<FabricanteResponse>> listarFabricantes() {
        List<Fabricante> fabricantes = fabricanteService.findAll();
        if (fabricantes.isEmpty()) { return ResponseEntity.noContent().build(); }
        List<FabricanteResponse> response = FabricanteMapper.toResponse(fabricantes);
        return ResponseEntity.ok(response);
    }
}
