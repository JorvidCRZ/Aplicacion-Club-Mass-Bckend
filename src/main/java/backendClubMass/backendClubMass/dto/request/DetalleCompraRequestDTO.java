package backendClubMass.backendClubMass.dto.request;

import lombok.Data;

@Data
public class DetalleCompraRequestDTO {
    private Integer idProducto;
    private Integer cantidad;
    private Double precioUnitario;
}
