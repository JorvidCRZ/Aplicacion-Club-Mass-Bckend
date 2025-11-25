package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente, Integer> {

    Cliente findByDni(String dni);
}