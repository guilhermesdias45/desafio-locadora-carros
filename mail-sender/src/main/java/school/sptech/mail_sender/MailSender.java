package school.sptech.mail_sender;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import school.sptech.mail_sender.service.MailService;

@Component
public class MailSender {

    private final MailService emailService;
    public MailSender(MailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${queue.name}")
    public void listen(String message) {
        System.out.println("Received: " + message);

        emailService.sendEmail(message);

        System.out.println("Email enviado");
    }

}
