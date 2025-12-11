package backendClubMass.backendClubMass.mapper.component;

import backendClubMass.backendClubMass.dto.response.PuntosResponse;
import backendClubMass.backendClubMass.model.Puntos;
import org.springframework.stereotype.Component;

@Component
public class PuntosMapper {

    public PuntosResponse toDTO(Puntos entity) {
        if (entity == null) return null;

        return PuntosResponse.builder()
                .idPuntos(entity.getIdPuntos())
                .idCliente(entity.getCliente().getIdCliente())
                .nombreCliente(
                        entity.getCliente().getUsuario().getNombre1() + " " +
                                entity.getCliente().getUsuario().getApellidoPaterno()
                )
                .idCampana(entity.getCampana().getIdCampana())
                .nombreCampana(entity.getCampana().getNombreCampana())
                .puntosGanados(entity.getPuntosGanados())
                .puntosCanjeados(entity.getPuntosCanjeados())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
