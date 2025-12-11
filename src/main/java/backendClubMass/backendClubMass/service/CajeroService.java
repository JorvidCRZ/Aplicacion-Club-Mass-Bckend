package backendClubMass.backendClubMass.service;

import backendClubMass.backendClubMass.dto.request.CajeroRequest;
import backendClubMass.backendClubMass.dto.response.CajeroResponse;

import java.util.List;

public interface CajeroService {
    List<CajeroResponse> getAllCajeros();
    List<CajeroResponse> getAllCajerosActivos();
    CajeroResponse getCajeroById(Integer id);
    CajeroResponse createCajero(CajeroRequest request);
    CajeroResponse updateCajero(Integer id, CajeroRequest request);
    boolean deleteCajero(Integer id);
    CajeroResponse cambiarEstadoCajero(Integer id, String estado);
}
