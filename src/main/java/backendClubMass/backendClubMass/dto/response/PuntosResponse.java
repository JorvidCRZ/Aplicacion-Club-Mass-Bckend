package backendClubMass.backendClubMass.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PuntosResponse {
    private Integer idPuntos;
    private Integer idCliente;
    private String nombreCliente;
    private Integer idCampana;
    private String nombreCampana;
    private BigDecimal puntosGanados;
    private BigDecimal puntosCanjeados;
    private LocalDateTime fechaRegistro;
}

