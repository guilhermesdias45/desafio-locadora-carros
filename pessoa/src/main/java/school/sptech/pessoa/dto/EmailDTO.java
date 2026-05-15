package school.sptech.pessoa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO {
    private String destinatario;
    private String nome;
    private String assunto;
    private String corpo;
    private String tipoUsuario;
}