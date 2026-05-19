package school.sptech.aluguel.service;

import tools.jackson.databind.ObjectMapper;
import dto.MailWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${queue.name}")
    private String queueName;

    public void enviarDadosAluguel(Object dadosAluguel) {
        try {
            log.info("Enfileirando dados do usuário: {}");

            MailWrapper wrapper = new MailWrapper(MailWrapper.Enum.ALUGUEL, dadosAluguel);

            String jsonMessage = objectMapper.writeValueAsString(wrapper);
            rabbitTemplate.convertAndSend(queueName, jsonMessage);

            log.info("Dados enfileirados na fila: {}", queueName);

        } catch (Exception e) {
            log.error("Erro ao enfileirar: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao enfileirar dados", e);
        }
    }
}