package backendClubMass.backendClubMass.service.serviceImpl;

import backendClubMass.backendClubMass.dao.AdministradorDAO;
import backendClubMass.backendClubMass.dao.UsuarioDAO;
import backendClubMass.backendClubMass.dto.request.AdminRequest;
import backendClubMass.backendClubMass.dto.response.AdminResponse;
import backendClubMass.backendClubMass.model.Administrador;
import backendClubMass.backendClubMass.model.Usuario;
import backendClubMass.backendClubMass.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorDAO administradorDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public List<AdminResponse> getAllAdmins() {
        return administradorDAO.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponse getAdminById(Integer id) {
        Administrador admin = administradorDAO.read(id);
        if (admin == null) throw new RuntimeException("Administrador no encontrado");
        return toResponse(admin);
    }

    @Override
    public AdminResponse createAdmin(AdminRequest request) {
        Usuario usuario = usuarioDAO.read(request.getIdUsuario());
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");
        if (!"ADMIN".equals(usuario.getRol().getNombreRol()))
            throw new RuntimeException("Usuario no tiene rol ADMIN");

        Administrador admin = new Administrador();
        admin.setUsuario(usuario);

        Administrador creado = administradorDAO.create(admin);
        return toResponse(creado);
    }

    @Override
    public AdminResponse updateAdmin(Integer id, AdminRequest request) {
        Administrador admin = administradorDAO.read(id);
        if (admin == null) throw new RuntimeException("Administrador no encontrado");

        Usuario usuario = usuarioDAO.read(request.getIdUsuario());
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");
        if (!"ADMIN".equals(usuario.getRol().getNombreRol()))
            throw new RuntimeException("Usuario no tiene rol ADMIN");

        admin.setUsuario(usuario);
        Administrador actualizado = administradorDAO.update(admin);
        return toResponse(actualizado);
    }

    @Override
    public boolean deleteAdmin(Integer id) {
        return administradorDAO.delete(id);
    }

    private AdminResponse toResponse(Administrador admin) {
        return new AdminResponse(
                admin.getIdAdmin(),
                admin.getUsuario().getIdUsuario(),
                admin.getUsuario().getNombre1(),
                admin.getUsuario().getApellidoPaterno(),
                admin.getUsuario().getUsername(),
                admin.getUsuario().getCorreo(),
                admin.getFechaRegistro()
        );
    }
}
