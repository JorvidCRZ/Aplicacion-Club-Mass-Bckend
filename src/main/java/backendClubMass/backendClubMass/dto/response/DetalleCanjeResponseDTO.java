package backendClubMass.backendClubMass.dto.response;

import lombok.Data;

@Data
public class DetalleCanjeResponseDTO {
    private Integer idDetalleCanje;
    private Integer idPremio;
    private String nombrePremio;
    private Integer cantidad;
    private Integer puntosPorUnidad;
    private Integer subtotalPuntos;
}
