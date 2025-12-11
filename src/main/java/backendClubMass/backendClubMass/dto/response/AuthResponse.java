package backendClubMass.backendClubMass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private Integer idUsuario;
    private String username;
    private String correo;
    private String rol;
}
