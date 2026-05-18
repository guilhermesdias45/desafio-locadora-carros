package dto;

public record MailWrapper(
        Enum tipo,
        Object data
) {
    public enum Enum {
        ALUGUEL,
        CADASTRO
    }
}
