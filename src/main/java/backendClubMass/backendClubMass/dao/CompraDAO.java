package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Compra;

import java.util.List;

public interface CompraDAO extends GenericDAO<Compra, Integer> {
    List<Compra> findByClienteId(Integer idCliente);
}