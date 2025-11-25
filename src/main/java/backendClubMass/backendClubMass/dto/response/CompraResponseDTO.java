package backendClubMass.backendClubMass.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CompraResponseDTO {

    private Integer idCompra;
    private LocalDateTime fechaCompra;
    private Double montoTotal;
    private String metodoPago;
    private Double puntosGenerados;

    private Integer idCliente;
    private String nombreCliente;

    private Integer idCajero;
    private String nombreCajero;
    private List<DetalleCompraResponseDTO> detalles;
}
