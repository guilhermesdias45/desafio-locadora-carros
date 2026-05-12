package school.sptech.carro.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.carro.Mapper.CarroMapper;
import school.sptech.carro.Mapper.FabricanteMapper;
import school.sptech.carro.Mapper.ModeloCarroMapper;
import school.sptech.carro.dto.carro.CarroRequest;
import school.sptech.carro.dto.fabricante.FabricanteRequest;
import school.sptech.carro.dto.modeloCarro.ModeloCarroRequest;
import school.sptech.carro.model.Carro;
import school.sptech.carro.model.Fabricante;
import school.sptech.carro.model.ModeloCarro;
import school.sptech.carro.service.CarroService;
import school.sptech.carro.service.FabricanteService;
import school.sptech.carro.service.ModeloCarroService;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroService carroService;
    private final ModeloCarroService modeloCarroService;
    private final FabricanteService fabricanteService;
    public CarroController(CarroService carroService, ModeloCarroService modeloCarroService, FabricanteService fabricanteService) {
        this.carroService = carroService;
        this.modeloCarroService = modeloCarroService;
        this.fabricanteService = fabricanteService;
    }

    @PostMapping
    public ResponseEntity<Carro> salvarCarro(@RequestBody @Valid CarroRequest carroRequest) {
        Carro salvo = carroService.save(CarroMapper.toEntity(carroRequest));
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/modelos")
    public ResponseEntity<ModeloCarro> salvarModeloCarro(@RequestBody @Valid ModeloCarroRequest modeloCarroRequest) {
        ModeloCarro salvo = modeloCarroService.save(ModeloCarroMapper.toEntity(modeloCarroRequest));
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/fabricantes")
    public ResponseEntity<Fabricante> salvarFabricante(@RequestBody @Valid FabricanteRequest fabricanteRequest) {
        Fabricante salvo = fabricanteService.save(FabricanteMapper.toEntity(fabricanteRequest));
        return ResponseEntity.ok(salvo);
    }
}
