package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Integer idCliente;
    private String telefono;
    private String dni;
    private String codigoMembresia;
    private LocalDateTime fechaRegistro;
    private Usuario usuario;
}