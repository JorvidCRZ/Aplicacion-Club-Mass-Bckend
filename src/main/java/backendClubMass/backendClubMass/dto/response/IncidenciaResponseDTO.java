package backendClubMass.backendClubMass.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidenciaResponseDTO {

    private Integer idIncidencia;
    private String descripcion;
    private String estado;
    private LocalDateTime fecha;

    private Integer idCliente;
    private String nombreCliente;
    private String apellidoCliente;
}
