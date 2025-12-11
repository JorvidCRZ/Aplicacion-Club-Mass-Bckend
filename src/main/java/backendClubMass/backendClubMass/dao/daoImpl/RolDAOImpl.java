package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.RolDAO;
import backendClubMass.backendClubMass.model.Rol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class RolDAOImpl implements RolDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Rol create(Rol rol) {
        entityManager.persist(rol);
        return rol;
    }

    @Override
    public Rol read(Integer idRol) {
        return entityManager.find(Rol.class, idRol);
    }

    @Override
    public Rol update(Rol rol) {
        return entityManager.merge(rol);
    }

    @Override
    public boolean delete(Integer idRol) {
        Rol rol = entityManager.find(Rol.class, idRol);
        if (rol != null) {
            entityManager.remove(rol);
            return true;
        }
        return false;
    }

    @Override
    public List<Rol> findAll() {
        // Usar el nombre de la clase y alias, no la tabla SQL
        return entityManager.createQuery("SELECT r FROM Rol r", Rol.class)
                .getResultList();
    }

    @Override
    public Optional<Rol> findByNombreRol(String nombreRol) {
        Rol rol = entityManager.createQuery(
                        "SELECT r FROM Rol r WHERE r.nombreRol = :nombreRol", Rol.class)
                .setParameter("nombreRol", nombreRol)
                .getResultStream()
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(rol);
    }
}
