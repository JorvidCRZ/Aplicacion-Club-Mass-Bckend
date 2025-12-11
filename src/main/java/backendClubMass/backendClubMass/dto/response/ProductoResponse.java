package backendClubMass.backendClubMass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductoResponse {
    private Integer idProducto;
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    private Integer estado;
    private String imagenUrl;
    private String descripcion;
}
