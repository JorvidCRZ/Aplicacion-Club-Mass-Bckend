package backendClubMass.backendClubMass.dto.request;

import lombok.Data;

@Data
public class ClienteRequestDTO {

    private String telefono;
    private String dni;
    private String codigoMembresia;
    private Integer idUsuario;
}
