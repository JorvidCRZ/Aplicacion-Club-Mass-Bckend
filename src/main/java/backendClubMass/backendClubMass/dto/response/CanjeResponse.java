package backendClubMass.backendClubMass.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CanjeResponse {
    private Integer idCanje;
    private Integer idCliente;
    private String nombreCliente;
    private Integer idPremio;
    private String nombrePremio;
    private Integer cantidad;
    private Integer puntosTotales;
    private LocalDateTime fecha;
}