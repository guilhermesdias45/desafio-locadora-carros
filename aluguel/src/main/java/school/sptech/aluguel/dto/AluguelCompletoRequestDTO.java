package school.sptech.aluguel.dto;

import school.sptech.aluguel.model.ApoliceSeguro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AluguelCompletoRequestDTO(
        Long id,
        LocalDate dataPedido,
        LocalDate dataEntrega,
        LocalDate dataDevolucao,
        BigDecimal valorTotal,
        ApoliceSeguro apolice,
        MotoristaRequestDTO motoristaId,
        CarroRequestDTO carroId
) {
    public record MotoristaRequestDTO(
            Long id,
            String nome,
            LocalDate dataNascimento,
            String cpf,
            String email,
            String sexo,
            Boolean ativo,
            String numeroCNH
    ) {}

    public record CarroRequestDTO(
            Long id,
            String placa,
            String chassi,
            String cor,
            BigDecimal valorDiaria
    ) {}
}
