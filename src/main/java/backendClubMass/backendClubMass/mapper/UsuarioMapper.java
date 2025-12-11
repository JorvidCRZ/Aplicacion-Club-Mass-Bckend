package backendClubMass.backendClubMass.mapper;

import backendClubMass.backendClubMass.dto.request.RegisterRequest;
import backendClubMass.backendClubMass.dto.response.UsuarioResponse;
import backendClubMass.backendClubMass.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "rol", ignore = true)
    Usuario toEntity(RegisterRequest dto);


    @Mapping(source = "rol.nombreRol", target = "rol")
    @Mapping(source = "rol.nombreRol", target = "nombreRol")
    UsuarioResponse toResponse(Usuario usuario);
}
