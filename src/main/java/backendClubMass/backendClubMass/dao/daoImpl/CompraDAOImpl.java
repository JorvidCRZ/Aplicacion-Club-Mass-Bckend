package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.CompraDAO;
import backendClubMass.backendClubMass.model.Compra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CompraDAOImpl implements CompraDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Compra create(Compra entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Compra read(Integer id) {
        return entityManager.find(Compra.class, id);
    }

    @Override
    public Compra update(Compra entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean delete(Integer id) {
        Compra compra = entityManager.find(Compra.class, id);
        if (compra != null) {
            entityManager.remove(compra);
            return true;
        }
        return false;
    }

    @Override
    public List<Compra> findAll() {
        return entityManager.createQuery("SELECT c FROM Compra c", Compra.class)
                .getResultList();
    }

    @Override
    public List<Compra> findByClienteId(Integer idCliente) {
        return entityManager.createQuery(
                        "SELECT c FROM Compra c WHERE c.cliente.idCliente = :idCliente", Compra.class)
                .setParameter("idCliente", idCliente)
                .getResultList();
    }
}
