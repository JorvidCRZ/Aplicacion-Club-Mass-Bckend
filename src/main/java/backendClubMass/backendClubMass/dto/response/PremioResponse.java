package backendClubMass.backendClubMass.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PremioResponse {
    private Integer idPremio;
    private String nombre;
    private String descripcion;
    private Integer puntosRequeridos;
    private Integer stock;
    private String imagen;
}
