package backendClubMass.backendClubMass.service.serviceImpl;

import backendClubMass.backendClubMass.dao.ClienteDAO;
import backendClubMass.backendClubMass.dao.UsuarioDAO;
import backendClubMass.backendClubMass.dto.request.ClienteRequest;
import backendClubMass.backendClubMass.dto.response.ClienteResponse;
import backendClubMass.backendClubMass.model.Cliente;
import backendClubMass.backendClubMass.model.Usuario;
import backendClubMass.backendClubMass.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public ClienteResponse createCliente(ClienteRequest request) {
        Usuario usuario = usuarioDAO.read(request.getIdUsuario());
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");
        if (!"CLIENTE".equals(usuario.getRol().getNombreRol()))
            throw new RuntimeException("Usuario no tiene rol CLIENTE");

        Cliente cliente = Cliente.builder()
                .usuario(usuario)
                .telefono(request.getTelefono())
                .dni(request.getDni())
                .codigoMembresia(request.getCodigoMembresia())
                .build();

        Cliente creado = clienteDAO.create(cliente);
        return toResponse(creado);
    }

    @Override
    public ClienteResponse updateCliente(Integer id, ClienteRequest request) {
        Cliente cliente = clienteDAO.read(id);
        if (cliente == null) throw new RuntimeException("Cliente no encontrado");

        if (request.getTelefono() != null) cliente.setTelefono(request.getTelefono());
        if (request.getDni() != null) cliente.setDni(request.getDni());
        if (request.getCodigoMembresia() != null) cliente.setCodigoMembresia(request.getCodigoMembresia());

        Cliente actualizado = clienteDAO.update(cliente);
        return toResponse(actualizado);
    }

    @Override
    public ClienteResponse getClienteById(Integer id) {
        Cliente cliente = clienteDAO.read(id);
        if (cliente == null) throw new RuntimeException("Cliente no encontrado");
        return toResponse(cliente);
    }

    @Override
    public ClienteResponse getClienteByDni(String dni) {
        return clienteDAO.findByDni(dni)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public ClienteResponse getClienteByCodigo(String codigo) {
        return clienteDAO.findByCodigoMembresia(codigo)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public List<ClienteResponse> getAllClientes() {
        return clienteDAO.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getIdCliente(),
                cliente.getUsuario().getIdUsuario(),
                cliente.getUsuario().getNombre1(),
                cliente.getUsuario().getApellidoPaterno(),
                cliente.getUsuario().getUsername(),
                cliente.getUsuario().getCorreo(),
                cliente.getTelefono(),
                cliente.getDni(),
                cliente.getCodigoMembresia(),
                cliente.getFechaRegistro()
        );
    }

    @Override
    public boolean deleteCliente(Integer id) {
        boolean eliminado = clienteDAO.delete(id);
        if (!eliminado) {
            throw new RuntimeException("Cliente no encontrado");
        }
        return true;
    }
}
