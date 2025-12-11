package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.PremioDAO;
import backendClubMass.backendClubMass.model.Premio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PremioDAOImpl implements PremioDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Premio create(Premio entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Premio read(Integer id) {
        return em.find(Premio.class, id);
    }

    @Override
    public Premio update(Premio entity) {
        return em.merge(entity);
    }

    @Override
    public boolean delete(Integer id) {
        Premio premio = read(id);
        if (premio == null) return false;
        em.remove(premio);
        return true;
    }

    @Override
    public List<Premio> findAll() {
        return em.createQuery("SELECT p FROM Premio p", Premio.class).getResultList();
    }
}
