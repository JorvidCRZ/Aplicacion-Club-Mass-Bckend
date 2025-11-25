package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campaña {

    private Integer idCampaña;
    private String nombreCampaña;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String descripcion;
    private Double multiplicadorPuntos;
    private Integer estado;
}
