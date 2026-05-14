package school.sptech.aluguel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.aluguel.dto.AluguelRequestDTO;
import school.sptech.aluguel.dto.AluguelResponseDTO;
import school.sptech.aluguel.mapper.AluguelMapper;
import school.sptech.aluguel.service.AluguelService;

import java.util.List;

@RestController
@RequestMapping("/alugueis")
@RequiredArgsConstructor
public class AluguelController {

    private final AluguelService service;

    @PostMapping
    public ResponseEntity<AluguelResponseDTO> cadastrar(AluguelRequestDTO dto){
        return ResponseEntity.status(201).body(AluguelMapper.toDto(service.salvar(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AluguelResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.status(200).body(AluguelMapper.toDto(service.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<AluguelResponseDTO>> listarTodos(){
        return ResponseEntity.status(200).body(AluguelMapper.toDto(service.listarTodos()));
    }

    @GetMapping("/carro/{id}")
    public ResponseEntity<List<AluguelResponseDTO>> buscarPorCarroId(@PathVariable Long id){
        return ResponseEntity.status(200).body(AluguelMapper.toDto(service.buscarPorCarro(id)));
    }

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<List<AluguelResponseDTO>> buscarPorPessoaId(@PathVariable Long id){
        return ResponseEntity.status(200).body(AluguelMapper.toDto(service.buscarPorCliente(id)));
    }
}
