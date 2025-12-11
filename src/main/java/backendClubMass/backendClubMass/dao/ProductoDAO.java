package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Producto;
import java.util.List;

public interface ProductoDAO extends GenericDAO<Producto, Integer> {
    List<Producto> findAllActivos();
}
