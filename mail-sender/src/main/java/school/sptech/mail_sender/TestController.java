package school.sptech.mail_sender;

import dto.AluguelMailDto;
import dto.MailWrapper;
import org.springframework.web.bind.annotation.*;
import school.sptech.mail_sender.service.MailService;
import tools.jackson.databind.ObjectMapper;

// classe para TESTES
// TODO remover classe
@RestController
public class TestController {

    MailService emailService;
    public TestController(MailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/test")
    public MailWrapper test(@RequestBody AluguelMailDto aluguelMailDto) {
        System.out.println(aluguelMailDto);
        MailWrapper mailWrapper = new MailWrapper(MailWrapper.Enum.ALUGUEL, aluguelMailDto);
        emailService.sendEmail(new ObjectMapper().writeValueAsString(mailWrapper));
        return mailWrapper;
    }
}
