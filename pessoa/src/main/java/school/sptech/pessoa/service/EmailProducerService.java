package school.sptech.pessoa.service;

import dto.MailWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import school.sptech.pessoa.dto.EmailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void enviarDadosUsuario(Object dadosUsuario) {
        try {
            log.info("Enfileirando dados do usuário: {}");

            log.info("Enfileirando dados do motorista: {}", motorista.getEmail());

            MailWrapper mailWrapper = new MailWrapper(MailWrapper.Enum.CADASTRO, payload);

            String jsonMessage = objectMapper.writeValueAsString(mailWrapper);


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