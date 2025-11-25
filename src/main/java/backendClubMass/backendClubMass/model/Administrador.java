package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {

    private Integer idAdmin;
    private Usuario usuario;
    private LocalDateTime fechaRegistro;
}