package backendClubMass.backendClubMass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AdminResponse {
    private Integer idAdmin;
    private Integer idUsuario;
    private String nombre1;
    private String apellidoPaterno;
    private String username;
    private String correo;
    private LocalDateTime fechaRegistro;
}
