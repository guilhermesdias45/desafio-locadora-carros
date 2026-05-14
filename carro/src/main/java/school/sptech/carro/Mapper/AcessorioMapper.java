package school.sptech.carro.Mapper;

import org.springframework.stereotype.Component;
import school.sptech.carro.dto.acessorio.AcessorioRequest;
import school.sptech.carro.dto.acessorio.AcessorioResponse;
import school.sptech.carro.model.Acessorio;

import java.util.List;

@Component
public class AcessorioMapper {

    public static Acessorio toEntity(AcessorioRequest request) {
        Acessorio acessorio = new Acessorio();
        acessorio.setDescricao(request.descricao());
        return acessorio;
    }

    public static AcessorioResponse toResponse(Acessorio acessorio) {
        return new AcessorioResponse(
                acessorio.getId(),
                acessorio.getDescricao()
        );
    }

    public static List<AcessorioResponse> toResponse(List<Acessorio> acessorios) {
        return acessorios.stream()
                .map(AcessorioMapper::toResponse)
                .toList();
    }
}
