package backendClubMass.backendClubMass.service.serviceImpl;

import backendClubMass.backendClubMass.dao.PuntosDAO;
import backendClubMass.backendClubMass.dto.response.PuntosResponse;
import backendClubMass.backendClubMass.mapper.component.PuntosMapper;
import backendClubMass.backendClubMass.service.PuntosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PuntosServiceImpl implements PuntosService {

    @Autowired
    private PuntosDAO puntosDAO;

    @Autowired
    private PuntosMapper puntosMapper;

    @Override
    public BigDecimal obtenerPuntosDisponibles(Integer idCliente) {
        return puntosDAO.getPuntosDisponibles(idCliente);
    }

    @Override
    public List<PuntosResponse> obtenerHistorialPuntos(Integer idCliente) {
        return puntosDAO.findByClienteId(idCliente)
                .stream()
                .map(puntosMapper::toDTO)
                .toList();
    }
}
