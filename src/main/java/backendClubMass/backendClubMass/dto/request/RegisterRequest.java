package backendClubMass.backendClubMass.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nombre1;
    private String nombre2;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String username;
    private String contrasena;
    private String correo;
    private Integer rolId;
    private String rol;
}