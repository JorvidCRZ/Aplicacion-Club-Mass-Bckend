package backendClubMass.backendClubMass.service.serviceImpl;

import backendClubMass.backendClubMass.dao.CajeroDAO;
import backendClubMass.backendClubMass.dao.UsuarioDAO;
import backendClubMass.backendClubMass.dto.request.CajeroRequest;
import backendClubMass.backendClubMass.dto.response.CajeroResponse;
import backendClubMass.backendClubMass.model.Cajero;
import backendClubMass.backendClubMass.model.Usuario;
import backendClubMass.backendClubMass.service.CajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CajeroServiceImpl implements CajeroService {

    @Autowired
    private CajeroDAO cajeroDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public List<CajeroResponse> getAllCajeros() {
        return cajeroDAO.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CajeroResponse> getAllCajerosActivos() {
        return cajeroDAO.findAllActivos().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CajeroResponse getCajeroById(Integer id) {
        Cajero cajero = cajeroDAO.read(id);
        if (cajero == null) throw new RuntimeException("Cajero no encontrado");
        return toResponse(cajero);
    }

    @Override
    public CajeroResponse createCajero(CajeroRequest request) {
        Usuario usuario = usuarioDAO.read(request.getIdUsuario());
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");
        if (!"CAJERO".equals(usuario.getRol().getNombreRol()))
            throw new RuntimeException("Usuario no tiene rol CAJERO");

        Cajero cajero = new Cajero();
        cajero.setUsuario(usuario);
        cajero.setEstado(request.getEstado());

        Cajero creado = cajeroDAO.create(cajero);
        return toResponse(creado);
    }

    @Override
    public CajeroResponse updateCajero(Integer id, CajeroRequest request) {
        Cajero cajero = cajeroDAO.read(id);
        if (cajero == null) throw new RuntimeException("Cajero no encontrado");

        if (request.getEstado() != null) cajero.setEstado(request.getEstado());
        if (request.getIdUsuario() != null) {
            Usuario usuario = usuarioDAO.read(request.getIdUsuario());
            if (usuario == null) throw new RuntimeException("Usuario no encontrado");
            if (!"CAJERO".equals(usuario.getRol().getNombreRol()))
                throw new RuntimeException("Usuario no tiene rol CAJERO");
            cajero.setUsuario(usuario);
        }

        Cajero actualizado = cajeroDAO.update(cajero);
        return toResponse(actualizado);
    }

    @Override
    public boolean deleteCajero(Integer id) {
        return cajeroDAO.delete(id);
    }

    @Override
    public CajeroResponse cambiarEstadoCajero(Integer id, String estado) {
        Cajero cajero = cajeroDAO.read(id);
        if (cajero == null) throw new RuntimeException("Cajero no encontrado");
        cajero.setEstado(estado);
        Cajero actualizado = cajeroDAO.update(cajero);
        return toResponse(actualizado);
    }

    private CajeroResponse toResponse(Cajero cajero) {
        return new CajeroResponse(
                cajero.getIdCajero(),
                cajero.getUsuario().getIdUsuario(),
                cajero.getUsuario().getNombre1(),
                cajero.getUsuario().getApellidoPaterno(),
                cajero.getUsuario().getUsername(),
                cajero.getUsuario().getCorreo(),
                cajero.getEstado(),
                cajero.getFechaRegistro()
        );
    }
}
