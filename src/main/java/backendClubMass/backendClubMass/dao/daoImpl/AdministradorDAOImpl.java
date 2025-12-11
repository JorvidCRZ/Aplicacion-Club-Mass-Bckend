package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.AdministradorDAO;
import backendClubMass.backendClubMass.model.Administrador;
import backendClubMass.backendClubMass.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AdministradorDAOImpl implements AdministradorDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Administrador create(Administrador entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Administrador read(Integer id) {
        return entityManager.find(Administrador.class, id);
    }

    @Override
    public Administrador update(Administrador entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean delete(Integer id) {
        Administrador admin = entityManager.find(Administrador.class, id);
        if (admin != null) {
            entityManager.remove(admin);
            return true;
        }
        return false;
    }

    @Override
    public List<Administrador> findAll() {
        return entityManager.createQuery("SELECT a FROM Administrador a", Administrador.class)
                .getResultList();
    }

    @Override
    public Optional<Administrador> findByUsuarioId(Integer idUsuario) {
        Administrador admin = entityManager.createQuery(
                        "SELECT a FROM Administrador a WHERE a.usuario.idUsuario = :idUsuario", Administrador.class)
                .setParameter("idUsuario", idUsuario)
                .getResultStream()
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(admin);
    }

    public boolean existsByUsuario(Usuario usuario) {
        return findByUsuarioId(usuario.getIdUsuario()).isPresent();
    }
}