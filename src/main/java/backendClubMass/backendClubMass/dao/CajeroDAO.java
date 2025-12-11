package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Cajero;
import java.util.List;

public interface CajeroDAO extends GenericDAO<Cajero, Integer> {
    List<Cajero> findAllActivos();
}