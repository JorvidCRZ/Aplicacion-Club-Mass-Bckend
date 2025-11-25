package backendClubMass.backendClubMass.dto.response;

import backendClubMass.backendClubMass.model.Rol;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioResponseDTO {

    private Integer idUsuario;
    private String nombre1;
    private String nombre2;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String username;
    private String correo;
    private Integer estado;
    private LocalDateTime fechaCreacion;
    private Rol rol;
}
