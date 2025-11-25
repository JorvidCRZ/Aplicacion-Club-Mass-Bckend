package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidencia {

    private Integer idIncidencia;
    private Cliente cliente;
    private String descripcion;
    private String estado;
    private LocalDateTime fecha;
}