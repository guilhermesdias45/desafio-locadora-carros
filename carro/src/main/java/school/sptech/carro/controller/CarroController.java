package school.sptech.carro.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.carro.Mapper.CarroMapper;
import school.sptech.carro.dto.carro.CarroRequest;
import school.sptech.carro.dto.carro.CarroResponse;
import school.sptech.carro.model.Carro;
import school.sptech.carro.service.AcessorioService;
import school.sptech.carro.service.CarroService;
import school.sptech.carro.service.FabricanteService;
import school.sptech.carro.service.ModeloCarroService;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService, ModeloCarroService modeloCarroService, FabricanteService fabricanteService, AcessorioService acessorioService) {
        this.carroService = carroService;
    }

    @PostMapping
    public ResponseEntity<CarroResponse> salvarCarro(@RequestBody @Valid CarroRequest carroRequest) {
        Carro salvo = carroService.save(CarroMapper.toEntity(carroRequest));
        return ResponseEntity.status(201).body(CarroMapper.toResponse(salvo));
    }

    @GetMapping
    public ResponseEntity<List<CarroResponse>> listarCarros() {
        List<Carro> carros = carroService.findAll();
        if (carros.isEmpty()) { return ResponseEntity.noContent().build(); }
        List<CarroResponse> response = CarroMapper.toResponse(carros);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/modelos/{id}")
    public ResponseEntity<List<CarroResponse>> filtrarPorModelo(@PathVariable Long id) {
        List<Carro> carros = carroService.findByModelo(id);
        if (carros.isEmpty()) { return ResponseEntity.noContent().build(); }
        List<CarroResponse> response = CarroMapper.toResponse(carros);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/acessorios/{id}")
    public ResponseEntity<List<CarroResponse>> filtrarPorAcessorio(@PathVariable Long id) {
        List<Carro> carros = carroService.findByAcessorio(id);
        if (carros.isEmpty()) { return ResponseEntity.noContent().build(); }
        List<CarroResponse> response = CarroMapper.toResponse(carros);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroResponse> buscarPorId(@PathVariable Long id) {
        Carro carro = carroService.buscarPorId(id);
        CarroResponse response = CarroMapper.toResponse(carro);
        return ResponseEntity.ok(response);
    }
}
