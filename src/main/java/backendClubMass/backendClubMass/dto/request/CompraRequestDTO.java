package backendClubMass.backendClubMass.dto.request;

import lombok.Data;

@Data
public class CompraRequestDTO {
    private Integer idCliente;
    private Integer idCajero;
    private Double montoTotal;
    private String metodoPago;
    private Double puntosGenerados;

}
