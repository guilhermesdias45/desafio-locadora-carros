package school.sptech.carro.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.carro.Mapper.ModeloCarroMapper;
import school.sptech.carro.dto.modeloCarro.ModeloCarroRequest;
import school.sptech.carro.dto.modeloCarro.ModeloCarroResponse;
import school.sptech.carro.model.ModeloCarro;
import school.sptech.carro.service.ModeloCarroService;

import java.util.List;

@RestController
@RequestMapping("/modelos")
public class ModeloCarroController {

    private final ModeloCarroService modeloCarroService;

    public ModeloCarroController(ModeloCarroService modeloCarroService) {
        this.modeloCarroService = modeloCarroService;
    }

    @PostMapping
    public ResponseEntity<ModeloCarroResponse> salvarModeloCarro(@RequestBody @Valid ModeloCarroRequest modeloCarroRequest) {
        ModeloCarro salvo = modeloCarroService.save(ModeloCarroMapper.toEntity(modeloCarroRequest));
        return ResponseEntity.status(201).body(ModeloCarroMapper.toResponse(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ModeloCarroResponse>> listarModeloCarros() {
        List<ModeloCarro> modelos = modeloCarroService.findAll();
        if (modelos.isEmpty()) { return ResponseEntity.noContent().build(); }
        List<ModeloCarroResponse> response = ModeloCarroMapper.toResponse(modelos);
        return ResponseEntity.ok(response);
    }
}
