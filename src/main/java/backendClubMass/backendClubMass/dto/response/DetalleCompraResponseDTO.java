package backendClubMass.backendClubMass.dto.response;

import lombok.Data;

@Data
public class DetalleCompraResponseDTO {
    private Integer idDetalle;
    private Integer cantidad;
    private Double precioUnitario;
    private String nombreProducto;
}
