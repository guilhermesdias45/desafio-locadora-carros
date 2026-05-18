package school.sptech.mail_sender.service;

import dto.MailWrapper;
import dto.PessoaMailDto;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final ObjectMapper objectMapper;
    public MailService(JavaMailSender mailSender, ObjectMapper objectMapper) {
        this.mailSender = mailSender;
        this.objectMapper = objectMapper;
    }

    public void sendEmail(String message) {
        if (message == null || message.isEmpty()) {
            throw new RuntimeException("Mensagem vazia. Email não enviado.");
        }


        MailWrapper wrapper = objectMapper.readValue(message, MailWrapper.class);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        switch (wrapper.tipo()) {
            case MailWrapper.Enum.ALUGUEL:

                break;
            case MailWrapper.Enum.CADASTRO:
                PessoaMailDto pessoa = objectMapper.convertValue(wrapper.data(), PessoaMailDto.class);
                mailMessage.setTo(pessoa.email());
                mailMessage.setSubject("Confirmção de Cadastro");
                mailMessage.setText("""
                        Olá, %s!
                        
                        Seu cadastro foi realizado com sucesso.
                        
                        Atenciosamente,
                        Locadora Carros
                        """.formatted(pessoa.nome()));
                break;

            default:
                throw new RuntimeException("Tipo de email desconhecido: " + wrapper.tipo());
        }

        try {
            //mailMessage.setTo("user@mail.com");
            //mailMessage.setSubject("Teste de envio de email");
            //mailMessage.setText("Mensagem recebida: " + message);
            mailSender.send(mailMessage);
        } catch (MailException e) {
            throw new RuntimeException("Erro ao enviar email: " + e.getMessage());
        }
    }

}
