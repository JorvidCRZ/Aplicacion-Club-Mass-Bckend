package backendClubMass.backendClubMass.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PremioRequest {
    private String nombre;
    private String descripcion;
    private Integer puntosRequeridos;
    private Integer stock;
    private String imagen;
}
