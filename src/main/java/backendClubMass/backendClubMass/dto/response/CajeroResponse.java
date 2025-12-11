package backendClubMass.backendClubMass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CajeroResponse {
    private Integer idCajero;
    private Integer idUsuario;
    private String nombre1;
    private String apellidoPaterno;
    private String username;
    private String correo;
    private String estado;
    private LocalDateTime fechaRegistro;
}
