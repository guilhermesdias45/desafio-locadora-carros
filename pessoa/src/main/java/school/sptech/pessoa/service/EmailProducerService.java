package school.sptech.pessoa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import school.sptech.pessoa.dto.EmailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import school.sptech.pessoa.model.Motorista;
import school.sptech.pessoa.model.Usuario;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void enviarDadosMotorista(Motorista motorista, Usuario usuario) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("motorista", motorista);
            payload.put("funcionario", usuario.getFuncionario());
            payload.put("matricula", usuario.getMatricula());

            log.info("Enfileirando dados do motorista: {}", motorista.getEmail());

            String jsonMessage = objectMapper.writeValueAsString(payload);
            rabbitTemplate.convertAndSend(queueName, jsonMessage);

            log.info("Dados enfileirados na fila: {}", queueName);
        } catch (Exception e) {
            log.error("Erro ao enfileirar: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao enfileirar dados", e);
        }
    }

    @Value("${queue.name}")
    private String queueName;
}