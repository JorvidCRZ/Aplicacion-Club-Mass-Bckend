package backendClubMass.backendClubMass.dto.request;

import lombok.Data;

@Data
public class ClienteRequest {
    private Integer idUsuario;
    private String telefono;
    private String dni;
    private String codigoMembresia;
}