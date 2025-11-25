package backendClubMass.backendClubMass.mapper;

import backendClubMass.backendClubMass.dto.request.ClienteRequestDTO;
import backendClubMass.backendClubMass.dto.response.ClienteResponseDTO;
import backendClubMass.backendClubMass.model.Cliente;
import backendClubMass.backendClubMass.model.Usuario;

public class ClienteMapper {

    public Cliente toEntity(ClienteRequestDTO dto, Usuario usuario) {
        Cliente cliente = new Cliente();
        cliente.setTelefono(dto.getTelefono());
        cliente.setDni(dto.getDni());
        cliente.setCodigoMembresia(dto.getCodigoMembresia());
        cliente.setUsuario(usuario);
        return cliente;
    }

    public ClienteResponseDTO toDTO(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();

        dto.setIdCliente(cliente.getIdCliente());
        dto.setTelefono(cliente.getTelefono());
        dto.setDni(cliente.getDni());
        dto.setCodigoMembresia(cliente.getCodigoMembresia());
        dto.setFechaRegistro(cliente.getFechaRegistro());

        if (cliente.getUsuario() != null) {
            dto.setUsername(cliente.getUsuario().getUsername());
            dto.setCorreo(cliente.getUsuario().getCorreo());
        }

        return dto;
    }
}