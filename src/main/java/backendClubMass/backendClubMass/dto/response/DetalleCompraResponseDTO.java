package backendClubMass.backendClubMass.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleCompraResponseDTO {
    private Integer idDetalle;
    private Integer cantidad;
    private double precioUnitario;
    private String nombreProducto;
}
