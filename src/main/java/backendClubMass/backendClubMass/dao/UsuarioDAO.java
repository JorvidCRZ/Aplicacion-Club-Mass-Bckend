package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Integer> {

    Usuario findByUsername(String username);
}
