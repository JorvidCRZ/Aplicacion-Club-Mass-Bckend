package backendClubMass.backendClubMass.mapper.component;

import backendClubMass.backendClubMass.dto.request.UsuarioRequest;
import backendClubMass.backendClubMass.dto.response.UsuarioResponse;
import backendClubMass.backendClubMass.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
    private RolMapper rolMapper;

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getIdUsuario(),
                usuario.getNombre1(),
                usuario.getNombre2(),
                usuario.getApellidoPaterno(),
                usuario.getApellidoMaterno(),
                usuario.getUsername(),
                usuario.getCorreo(),
                usuario.getRol() != null ? usuario.getRol().getNombreRol() : null,
                String.valueOf(usuario.getEstado()),
                rolMapper.toResponse(usuario.getRol()) != null ? rolMapper.toResponse(usuario.getRol()).getNombreRol() : null
        );
    }


    public Usuario toEntity(UsuarioRequest request) {
        if (request == null) return null;
        Usuario usuario = Usuario.builder()
                .nombre1(request.getNombre1())
                .nombre2(request.getNombre2())
                .apellidoPaterno(request.getApellidoPaterno())
                .apellidoMaterno(request.getApellidoMaterno())
                .username(request.getUsername())
                .contrasena(request.getContrasena())
                .correo(request.getCorreo())
                .estado(request.getEstado() != null ? request.getEstado() : 1)
                .build();
        return usuario;
    }

    public void updateEntityFromRequest(UsuarioRequest request, Usuario usuario) {
        if (request.getNombre1() != null) usuario.setNombre1(request.getNombre1());
        if (request.getNombre2() != null) usuario.setNombre2(request.getNombre2());
        if (request.getApellidoPaterno() != null) usuario.setApellidoPaterno(request.getApellidoPaterno());
        if (request.getApellidoMaterno() != null) usuario.setApellidoMaterno(request.getApellidoMaterno());
        if (request.getUsername() != null) usuario.setUsername(request.getUsername());
        if (request.getContrasena() != null) usuario.setContrasena(request.getContrasena());
        if (request.getCorreo() != null) usuario.setCorreo(request.getCorreo());
        if (request.getEstado() != null) usuario.setEstado(request.getEstado());
    }
}
