package school.sptech.carro.Mapper;

import org.springframework.stereotype.Component;
import school.sptech.carro.dto.carro.CarroRequest;
import school.sptech.carro.dto.carro.CarroResponse;
import school.sptech.carro.model.Acessorio;
import school.sptech.carro.model.Carro;
import school.sptech.carro.model.Fabricante;
import school.sptech.carro.model.ModeloCarro;

import java.util.List;
import java.util.stream.Collectors;

public class CarroMapper {

    public static Carro toEntity(CarroRequest request) {
        Carro carro = new Carro();
        carro.setPlaca(request.placa());
        carro.setChassi(request.chassi());
        carro.setCor(request.cor());
        carro.setValorDiaria(request.valorDiaria());

        carro.setModeloCarro(new ModeloCarro());
        carro.getModeloCarro().setId(request.modeloCarroId());

        request.acessorioIds()
                .forEach(id -> {
                    Acessorio acessorio = new Acessorio();
                    acessorio.setId(id);
                    carro.getAcessorios().add(acessorio);
                });

        return carro;
    }

    public static CarroResponse toResponse(Carro carro) {
        return new CarroResponse(
                carro.getId(),
                carro.getPlaca(),
                carro.getChassi(),
                carro.getCor(),
                carro.getValorDiaria(),
                new CarroResponse.ModeloCarroResponse(
                        carro.getModeloCarro().getId(),
                        carro.getModeloCarro().getDescricao(),
                        carro.getModeloCarro().getCategoria(),
                        new CarroResponse.ModeloCarroResponse.FabricanteResponse(
                                carro.getModeloCarro().getFabricante().getId(),
                                carro.getModeloCarro().getFabricante().getNome()
                        )
                ),
                carro.getAcessorios().stream()
                        .map(acessorio -> new CarroResponse.AcessorioResponse(
                                acessorio.getId(),
                                acessorio.getDescricao()
                        ))
                        .collect(Collectors.toList())
        );
    }

    public static List<CarroResponse> toResponse(List<Carro> carros) {
        return carros.stream()
                .map(CarroMapper::toResponse)
                .toList();
    }
}
