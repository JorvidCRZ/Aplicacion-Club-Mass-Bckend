package backendClubMass.backendClubMass.service.serviceImpl;

import backendClubMass.backendClubMass.dao.RolDAO;
import backendClubMass.backendClubMass.dao.UsuarioDAO;
import backendClubMass.backendClubMass.dto.request.LoginRequest;
import backendClubMass.backendClubMass.dto.request.RegisterRequest;
import backendClubMass.backendClubMass.dto.response.AuthResponse;
import backendClubMass.backendClubMass.model.Rol;
import backendClubMass.backendClubMass.model.Usuario;
import backendClubMass.backendClubMass.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private RolDAO rolDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest request) {

        Usuario usuario = usuarioDAO.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // VALIDAR CONTRASEÑA ENCRIPTADA
        if (!passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new AuthResponse(
                usuario.getIdUsuario(),
                usuario.getUsername(),
                usuario.getCorreo(),
                usuario.getRol().getNombreRol()
        );
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (usuarioDAO.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username ya existe");

        if (usuarioDAO.existsByCorreo(request.getCorreo()))
            throw new RuntimeException("Correo ya existe");

        Rol rol = rolDAO.findByNombreRol(request.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no existe"));

        Usuario usuario = Usuario.builder()
                .nombre1(request.getNombre1())
                .nombre2(request.getNombre2())
                .apellidoPaterno(request.getApellidoPaterno())
                .apellidoMaterno(request.getApellidoMaterno())
                .username(request.getUsername())
                // ENCRIPTAR AQUÍ
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .correo(request.getCorreo())
                .estado(1)
                .rol(rol)
                .build();

        usuarioDAO.create(usuario);

        return new AuthResponse(
                usuario.getIdUsuario(),
                usuario.getUsername(),
                usuario.getCorreo(),
                usuario.getRol().getNombreRol()
        );
    }
}
