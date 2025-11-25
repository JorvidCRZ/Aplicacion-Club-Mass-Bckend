package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Incidencia;

import java.util.List;

public interface IncidenciaDAO extends GenericDAO<Incidencia, Integer> {

    List<Incidencia> findByCliente(Integer idCliente);
}