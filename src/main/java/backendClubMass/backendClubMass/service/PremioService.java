package backendClubMass.backendClubMass.service;

import backendClubMass.backendClubMass.dto.request.PremioRequest;
import backendClubMass.backendClubMass.dto.response.PremioResponse;

import java.util.List;

public interface PremioService {
    List<PremioResponse> findAll();
    PremioResponse create(PremioRequest request);
    PremioResponse update(Integer id, PremioRequest request);
    PremioResponse updateStock(Integer id, Integer nuevoStock);
}
