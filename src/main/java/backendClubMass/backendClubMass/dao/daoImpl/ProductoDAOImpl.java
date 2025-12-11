package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.ProductoDAO;
import backendClubMass.backendClubMass.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public class ProductoDAOImpl implements ProductoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Producto create(Producto entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Producto read(Integer id) {
        return entityManager.find(Producto.class, id);
    }

    @Override
    public Producto update(Producto entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean delete(Integer id) {
        Producto producto = entityManager.find(Producto.class, id);
        if (producto != null) {
            entityManager.remove(producto);
            return true;
        }
        return false;
    }

    @Override
    public List<Producto> findAll() {
        return entityManager.createQuery("SELECT p FROM Producto p", Producto.class)
                .getResultList();
    }

    @Override
    public List<Producto> findAllActivos() {
        return entityManager.createQuery("SELECT p FROM Producto p WHERE p.estado = 1", Producto.class)
                .getResultList();
    }
}
