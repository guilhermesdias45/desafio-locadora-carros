package school.sptech.mail_sender.service;

import dto.AluguelMailDto;
import dto.MailWrapper;
import dto.MotoristaMailDto;
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
                AluguelMailDto aluguel = objectMapper.convertValue(wrapper.data(), AluguelMailDto.class);
                mailMessage.setTo(aluguel.motorista().email());
                mailMessage.setSubject("Confirmção de Aluguel");
                mailMessage.setText("""
                        Olá, %s!
                        
                        Seu Aluguel foi realizado com sucesso.
                        
                        Informações do seu Alugel:
                        %s
                        
                        Atenciosamente,
                        Locadora Carros
                        """.formatted(aluguel.motorista().nome(), aluguel));
                break;

            case MailWrapper.Enum.CADASTRO:
                MotoristaMailDto motorista = objectMapper.convertValue(wrapper.data(), MotoristaMailDto.class);

                if (motorista.funcionario()) {
                    mailMessage.setTo(motorista.usuario().email());
                    mailMessage.setSubject("Confirmção de Cadastro de Funcionário");
                    mailMessage.setText("""
                        Olá, %s!
                        
                        Seu cadastro de funcionário foi realizado com sucesso.
                        Matrícula: %s
                        
                        
                        Atenciosamente,
                        Locadora Carros
                        """.formatted(motorista.usuario().nome(),
                            motorista.matricula().isBlank() ? "N/A" : motorista.matricula()));
                    break;
                }

                mailMessage.setTo(motorista.usuario().email());
                mailMessage.setSubject("Confirmção de Cadastro");
                mailMessage.setText("""
                        Olá, %s!
                        
                        Seu cadastro foi realizado com sucesso.
                        
                        Atenciosamente,
                        Locadora Carros
                        """.formatted(motorista.usuario().nome()));
                break;

            default:
                throw new RuntimeException("Tipo de email desconhecido: " + wrapper.tipo());
        }

        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            throw new RuntimeException("Erro ao enviar email: " + e.getMessage());
        }
    }

}
