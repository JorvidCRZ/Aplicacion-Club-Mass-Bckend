package backendClubMass.backendClubMass.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CanjeResponseDTO {

    private Integer idCanje;
    private LocalDateTime fechaCanje;
    private Integer idCliente;
    private String nombreCliente;

    private Integer idCajero;
    private String nombreCajero;

    private Double puntosUsados;

    private List<DetalleCanjeResponseDTO> detalles;
}
