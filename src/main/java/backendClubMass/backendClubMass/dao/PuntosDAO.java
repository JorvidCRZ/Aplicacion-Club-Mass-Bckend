package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Puntos;
import java.math.BigDecimal;
import java.util.List;

public interface PuntosDAO extends GenericDAO<Puntos, Integer> {
        List<Puntos> findByClienteId(Integer idCliente);
        BigDecimal getPuntosDisponibles(Integer idCliente);
}
