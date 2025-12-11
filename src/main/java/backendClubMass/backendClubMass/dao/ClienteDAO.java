package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Cliente;
import java.util.Optional;

public interface ClienteDAO extends GenericDAO<Cliente, Integer> {
    Optional<Cliente> findByDni(String dni);
    Optional<Cliente> findByCodigoMembresia(String codigo);
}