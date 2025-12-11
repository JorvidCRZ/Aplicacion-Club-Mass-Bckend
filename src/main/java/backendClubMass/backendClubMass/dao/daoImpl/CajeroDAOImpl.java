package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.CajeroDAO;
import backendClubMass.backendClubMass.model.Cajero;
import backendClubMass.backendClubMass.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public class CajeroDAOImpl implements CajeroDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cajero create(Cajero entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Cajero read(Integer id) {
        return entityManager.find(Cajero.class, id);
    }

    @Override
    public Cajero update(Cajero entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean delete(Integer id) {
        Cajero cajero = entityManager.find(Cajero.class, id);
        if(cajero != null){
            entityManager.remove(cajero);
            return true;
        }
        return false;
    }

    @Override
    public List<Cajero> findAll() {
        return entityManager.createQuery("SELECT c FROM Cajero c", Cajero.class).getResultList();
    }

    @Override
    public List<Cajero> findAllActivos() {
        return entityManager.createQuery("SELECT c FROM Cajero c WHERE c.estado = 'ACTIVO'", Cajero.class).getResultList();
    }

    public boolean existsByUsuario(Usuario usuario) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(c) FROM Cajero c WHERE c.usuario = :usuario", Long.class)
                .setParameter("usuario", usuario)
                .getSingleResult();
        return count > 0;
    }

}
