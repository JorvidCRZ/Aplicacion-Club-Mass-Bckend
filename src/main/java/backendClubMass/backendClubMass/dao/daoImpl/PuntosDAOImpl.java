package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.PuntosDAO;
import backendClubMass.backendClubMass.model.Puntos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class PuntosDAOImpl implements PuntosDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Puntos create(Puntos entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Puntos read(Integer id) {
        return entityManager.find(Puntos.class, id);
    }

    @Override
    public Puntos update(Puntos entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean delete(Integer id) {
        Puntos puntos = entityManager.find(Puntos.class, id);
        if (puntos != null) {
            entityManager.remove(puntos);
            return true;
        }
        return false;
    }

    @Override
    public List<Puntos> findAll() {
        return entityManager.createQuery("SELECT p FROM Puntos p", Puntos.class).getResultList();
    }

    @Override
    public List<Puntos> findByClienteId(Integer idCliente) {
        return entityManager.createQuery(
                        "SELECT p FROM Puntos p WHERE p.cliente.idCliente = :idCliente ORDER BY p.fechaRegistro DESC", Puntos.class)
                .setParameter("idCliente", idCliente)
                .getResultList();
    }

    @Override
    public BigDecimal getPuntosDisponibles(Integer idCliente) {
        BigDecimal result = entityManager.createQuery(
                        "SELECT SUM(p.puntosGanados - p.puntosCanjeados) FROM Puntos p WHERE p.cliente.idCliente = :idCliente", BigDecimal.class)
                .setParameter("idCliente", idCliente)
                .getSingleResult();
        return result != null ? result : BigDecimal.ZERO;
    }
}
