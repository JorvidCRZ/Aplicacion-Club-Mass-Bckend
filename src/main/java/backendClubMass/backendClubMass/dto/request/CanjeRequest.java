package backendClubMass.backendClubMass.dto.request;

import lombok.Data;

@Data
public class CanjeRequest {
    private Integer idCliente;
    private Integer idPremio;
    private Integer cantidad;
}
