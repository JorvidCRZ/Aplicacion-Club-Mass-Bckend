package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Puntos;

import java.util.List;

public interface PuntosDAO extends GenericDAO<Puntos, Integer> {

    List<Puntos> findByCliente(Integer idCliente);

    Double getPuntosDisponibles(Integer idCliente);
}