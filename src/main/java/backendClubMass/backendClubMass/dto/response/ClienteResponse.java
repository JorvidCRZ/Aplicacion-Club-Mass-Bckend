package backendClubMass.backendClubMass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ClienteResponse {
    private Integer idCliente;
    private Integer idUsuario;
    private String nombre1;
    private String apellidoPaterno;
    private String username;
    private String correo;
    private String telefono;
    private String dni;
    private String codigoMembresia;
    private LocalDateTime fechaRegistro;
}
