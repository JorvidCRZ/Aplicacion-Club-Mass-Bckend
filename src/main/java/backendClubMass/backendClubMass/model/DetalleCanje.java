package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCanje {

    private Integer idDetalleCanje;
    private Canje canje;
    private Premio premio;
    private Integer cantidad;
    private Integer puntosPorUnidad;
    private Integer subtotalPuntos;
}