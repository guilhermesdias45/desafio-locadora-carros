package school.sptech.aluguel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.aluguel.dto.AluguelRequestDTO;
import school.sptech.aluguel.dto.AluguelResponseDTO;
import school.sptech.aluguel.mapper.AluguelMapper;
import school.sptech.aluguel.service.AluguelService;

@RestController
@RequestMapping("/alugueis")
@AllArgsConstructor
public class AluguelController {

    private AluguelService service;

    @PostMapping
    public ResponseEntity<AluguelResponseDTO> cadastrar(AluguelRequestDTO dto){
        return ResponseEntity.status(201).body(AluguelMapper.toDto(service.salvar(dto)));
    }

    @GetMapping
    public ResponseEntity<AluguelResponseDTO> buscarPorId(Long id){
        return ResponseEntity.status(200).body(AluguelMapper.toDto(service.buscarPorId(id)));
    }
}
