package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.UsuarioDAO;
import backendClubMass.backendClubMass.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Usuario create(Usuario usuario) {
        entityManager.persist(usuario);
        return usuario;
    }

    @Override
    public Usuario read(Integer idUsuario) {
        return entityManager.find(Usuario.class, idUsuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return entityManager.merge(usuario);
    }

    @Override
    public boolean delete(Integer idUsuario) {
        Usuario usuario = entityManager.find(Usuario.class, idUsuario);
        if (usuario != null) {
            entityManager.remove(usuario);
            return true;
        }
        return false;
    }

    @Override
    public List<Usuario> findAll() {
        // usar nombre de clase y alias
        return entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList();
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        Usuario usuario = entityManager.createQuery(
                        "SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(usuario);
    }

    @Override
    public boolean existsByUsername(String username) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(u) FROM Usuario u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByCorreo(String correo) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(u) FROM Usuario u WHERE u.correo = :correo", Long.class)
                .setParameter("correo", correo)
                .getSingleResult();
        return count > 0;
    }
}
