package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Puntos {

    private Integer idPuntos;
    private Cliente cliente;
    private Campaña campaña;

    private Double puntosGanados;
    private Double puntosCanjeados;
    private LocalDateTime fechaRegistro;
}