package backendClubMass.backendClubMass.dto.request;

import lombok.Data;

@Data
public class UsuarioRequestDTO {

    private String nombre1;
    private String nombre2;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String username;
    private String password;
    private String correo;
    private Integer rol_id;
}
