package backendClubMass.backendClubMass.service;


import backendClubMass.backendClubMass.dto.request.CompraRequest;
import backendClubMass.backendClubMass.dto.response.CompraResponse;
import java.util.List;

public interface CompraService {
    CompraResponse createCompra(CompraRequest request);
    CompraResponse getCompraById(Integer id);
    List<CompraResponse> getComprasByCliente(Integer idCliente);
}