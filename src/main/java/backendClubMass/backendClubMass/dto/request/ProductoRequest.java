package backendClubMass.backendClubMass.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductoRequest {
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    private Integer estado;
    private String imagenUrl;
    private String descripcion;
}
