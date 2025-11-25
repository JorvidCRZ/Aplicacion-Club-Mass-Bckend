package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Canje {

    private Integer idCanje;
    private LocalDateTime fechaCanje;
    private Cliente cliente;
    private Cajero cajero;
    private Double puntosUsados;
}