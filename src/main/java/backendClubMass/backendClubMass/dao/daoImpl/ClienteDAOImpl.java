package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.ClienteDAO;
import backendClubMass.backendClubMass.model.Cliente;
import backendClubMass.backendClubMass.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ClienteDAOImpl implements ClienteDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cliente create(Cliente entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Cliente read(Integer id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
    public Cliente update(Cliente entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean delete(Integer id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        if (cliente != null) {
            entityManager.remove(cliente);
            return true;
        }
        return false;
    }

    @Override
    public List<Cliente> findAll() {
        return entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }

    @Override
    public Optional<Cliente> findByDni(String dni) {
        Cliente cliente = entityManager.createQuery(
                        "SELECT c FROM Cliente c WHERE c.dni = :dni", Cliente.class)
                .setParameter("dni", dni)
                .getResultStream()
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(cliente);
    }

    @Override
    public Optional<Cliente> findByCodigoMembresia(String codigo) {
        Cliente cliente = entityManager.createQuery(
                        "SELECT c FROM Cliente c WHERE c.codigoMembresia = :codigo", Cliente.class)
                .setParameter("codigo", codigo)
                .getResultStream()
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(cliente);
    }

    public boolean existsByUsuario(Usuario usuario) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(c) FROM Cliente c WHERE c.usuario = :usuario", Long.class)
                .setParameter("usuario", usuario)
                .getSingleResult();
        return count > 0;
    }
}
