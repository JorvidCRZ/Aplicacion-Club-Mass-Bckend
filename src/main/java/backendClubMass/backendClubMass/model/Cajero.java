package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cajero {

    private Integer idCajero;
    private Usuario usuario;
    private LocalDateTime fechaRegistro;
    private String estado;
}