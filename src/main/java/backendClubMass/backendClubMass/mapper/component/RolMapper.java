package backendClubMass.backendClubMass.mapper.component;

import backendClubMass.backendClubMass.dto.response.RolResponse;
import backendClubMass.backendClubMass.model.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {

    public RolResponse toResponse(Rol rol) {
        if (rol == null) return null;
        return new RolResponse(rol.getIdRol(), rol.getNombreRol());
    }
}
