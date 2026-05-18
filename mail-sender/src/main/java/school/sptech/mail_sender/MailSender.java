package school.sptech.mail_sender;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSender {

    private final JavaMailSender mailSender;
    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = "${queue.name}")
    public void listen(String message) {
        System.out.println("Received: " + message);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("user@mail.com");
        mailMessage.setSubject("Teste de envio de email");
        mailMessage.setText("Mensagen recebida: " + message);

        mailSender.send(mailMessage);
        System.out.println("Email enviado");
    }

}
