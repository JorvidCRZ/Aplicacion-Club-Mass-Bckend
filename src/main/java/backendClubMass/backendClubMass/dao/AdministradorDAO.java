package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Administrador;
import java.util.Optional;

public interface AdministradorDAO extends GenericDAO<Administrador, Integer> {
    Optional<Administrador> findByUsuarioId(Integer idUsuario);
}