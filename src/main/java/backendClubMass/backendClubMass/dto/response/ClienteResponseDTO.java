package backendClubMass.backendClubMass.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ClienteResponseDTO {

    private Integer idCliente;
    private String telefono;
    private String dni;
    private String codigoMembresia;
    private LocalDateTime fechaRegistro;

    private String username;
    private String correo;
}
