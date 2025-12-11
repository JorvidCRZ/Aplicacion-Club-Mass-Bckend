package backendClubMass.backendClubMass.service;

import backendClubMass.backendClubMass.dto.request.ClienteRequest;
import backendClubMass.backendClubMass.dto.response.ClienteResponse;
import java.util.List;

public interface ClienteService {
    ClienteResponse createCliente(ClienteRequest request);
    ClienteResponse updateCliente(Integer id, ClienteRequest request);
    ClienteResponse getClienteById(Integer id);
    ClienteResponse getClienteByDni(String dni);
    ClienteResponse getClienteByCodigo(String codigo);
    List<ClienteResponse> getAllClientes();
    boolean deleteCliente(Integer id);
}
