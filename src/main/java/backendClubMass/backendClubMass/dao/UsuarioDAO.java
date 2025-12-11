package backendClubMass.backendClubMass.dao;

import backendClubMass.backendClubMass.model.Usuario;
import java.util.Optional;

public interface UsuarioDAO extends GenericDAO<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByCorreo(String correo);
}
