package backendClubMass.backendClubMass.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleCompraRequest {
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
