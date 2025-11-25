package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Premio {

    private Integer idPremio;
    private String nombre;
    private String descripcion;
    private Integer puntosRequeridos;
    private Integer stock;
    private String imagen;
}