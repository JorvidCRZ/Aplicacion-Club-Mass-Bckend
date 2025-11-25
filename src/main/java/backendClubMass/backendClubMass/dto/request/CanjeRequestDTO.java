package backendClubMass.backendClubMass.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class CanjeRequestDTO {

    private Integer idCliente;
    private Integer idCajero;
    private Double puntosUsados;

    private List<DetalleCanjeRequestDTO> detalles;
}

