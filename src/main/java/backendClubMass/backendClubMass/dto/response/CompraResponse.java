package backendClubMass.backendClubMass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class CompraResponse {
    private Integer idCompra;
    private LocalDateTime fechaCompra;
    private BigDecimal montoTotal;
    private String metodoPago;
    private BigDecimal puntosGenerados;
    private Integer idCliente;
    private String nombreCliente;
    private Integer idCajero;
    private String nombreCajero;
    private List<DetalleCompraResponse> detalles;
}
