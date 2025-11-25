package backendClubMass.backendClubMass.dao;


import java.util.List;

public interface GenericDAO<T, ID> {
    T create(T entity);
    T read(ID id);
    T update(T entity);
    boolean delete(ID id);
    List<T> findAll();
}