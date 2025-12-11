package backendClubMass.backendClubMass.service;

import backendClubMass.backendClubMass.dto.response.PuntosResponse;

import java.math.BigDecimal;
import java.util.List;

public interface PuntosService {
    BigDecimal obtenerPuntosDisponibles(Integer idCliente);
    List<PuntosResponse> obtenerHistorialPuntos(Integer idCliente);
}
