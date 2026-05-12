package school.sptech.carro.Mapper;

import org.springframework.stereotype.Component;
import school.sptech.carro.dto.carro.CarroRequest;
import school.sptech.carro.model.Acessorio;
import school.sptech.carro.model.Carro;
import school.sptech.carro.model.ModeloCarro;

import java.util.List;

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

        /* for (Long id : request.acessorioIds()) {
            Acessorio acessorio = new Acessorio();
            acessorio.setId(id);
            carro.getAcessorios().add(acessorio);
        } */

        return carro;
    }
}
