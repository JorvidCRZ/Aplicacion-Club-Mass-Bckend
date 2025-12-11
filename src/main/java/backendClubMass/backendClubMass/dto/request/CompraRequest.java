package backendClubMass.backendClubMass.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CompraRequest {
    private Integer idCliente;
    private Integer idCajero;
    private String metodoPago;
    private List<DetalleCompraRequest> detalles;
}