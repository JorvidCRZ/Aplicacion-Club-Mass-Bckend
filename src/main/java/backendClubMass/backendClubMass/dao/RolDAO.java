package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Rol;

import java.util.Optional;

public interface RolDAO extends GenericDAO<Rol, Integer> {
    Optional<Rol> findByNombreRol(String nombreRol);
}
