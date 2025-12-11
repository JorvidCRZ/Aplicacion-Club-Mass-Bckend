package backendClubMass.backendClubMass.dto.request;

import lombok.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PuntosRequest {
    private Integer idCliente;
    private Integer idCampana;
    private BigDecimal puntosGanados;
    private BigDecimal puntosCanjeados;
}
