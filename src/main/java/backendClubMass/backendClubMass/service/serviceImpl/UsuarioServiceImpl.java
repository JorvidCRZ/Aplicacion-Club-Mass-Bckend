package backendClubMass.backendClubMass.service.serviceImpl;

import backendClubMass.backendClubMass.dao.UsuarioDAO;
import backendClubMass.backendClubMass.dao.RolDAO;
import backendClubMass.backendClubMass.dto.request.UsuarioRequest;
import backendClubMass.backendClubMass.dto.response.UsuarioResponse;
import backendClubMass.backendClubMass.model.Usuario;
import backendClubMass.backendClubMass.model.Rol;
import backendClubMass.backendClubMass.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private RolDAO rolDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioResponse> getAllUsuarios() {
        return usuarioDAO.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse getUsuarioById(Integer id) {
        Usuario usuario = usuarioDAO.read(id);
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");
        return toResponse(usuario);
    }

    @Override
    public UsuarioResponse createUsuario(UsuarioRequest request) {
        if (usuarioDAO.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username ya existe");
        if (usuarioDAO.existsByCorreo(request.getCorreo()))
            throw new RuntimeException("Correo ya existe");

        Rol rol = rolDAO.findByNombreRol(request.getRol().getNombreRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = Usuario.builder()
                .nombre1(request.getNombre1())
                .nombre2(request.getNombre2())
                .apellidoPaterno(request.getApellidoPaterno())
                .apellidoMaterno(request.getApellidoMaterno())
                .username(request.getUsername())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .correo(request.getCorreo())
                .estado(request.getEstado() != null ? request.getEstado() : 1)
                .rol(rol)
                .build();

        Usuario creado = usuarioDAO.create(usuario);
        return toResponse(creado);
    }

    @Override
    public UsuarioResponse updateUsuario(Integer id, UsuarioRequest request) {
        Usuario usuario = usuarioDAO.read(id);
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");

        if (request.getNombre1() != null) usuario.setNombre1(request.getNombre1());
        if (request.getNombre2() != null) usuario.setNombre2(request.getNombre2());
        if (request.getApellidoPaterno() != null) usuario.setApellidoPaterno(request.getApellidoPaterno());
        if (request.getApellidoMaterno() != null) usuario.setApellidoMaterno(request.getApellidoMaterno());
        if (request.getUsername() != null) usuario.setUsername(request.getUsername());
        if (request.getContrasena() != null) usuario.setContrasena(passwordEncoder.encode(request.getContrasena()));
        if (request.getCorreo() != null) usuario.setCorreo(request.getCorreo());
        if (request.getRol() != null) {
            Rol rol = rolDAO.findByNombreRol(request.getRol().getNombreRol())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            usuario.setRol(rol);
        }
        if (request.getEstado() != null) usuario.setEstado(request.getEstado());

        Usuario actualizado = usuarioDAO.update(usuario);
        return toResponse(actualizado);
    }

    @Override
    public UsuarioResponse cambiarEstadoUsuario(Integer id, Integer estado) {
        Usuario usuario = usuarioDAO.read(id);
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");
        usuario.setEstado(estado);
        Usuario actualizado = usuarioDAO.update(usuario);
        return toResponse(actualizado);
    }

    @Override
    public boolean deleteUsuario(Integer id) {
        Usuario usuario = usuarioDAO.read(id);
        if (usuario == null) return false;
        return usuarioDAO.delete(id);
    }

    private UsuarioResponse toResponse(Usuario usuario) {
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
                usuario.getRol() != null ? usuario.getRol().getNombreRol() : null
        );
    }

}
