package backendClubMass.backendClubMass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioResponse {
    private Integer idUsuario;
    private String nombre1;
    private String nombre2;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String username;
    private String correo;
    private String rol;
    private String estado;
    private String nombreRol;
}