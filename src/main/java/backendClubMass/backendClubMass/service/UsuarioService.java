package backendClubMass.backendClubMass.service;

import backendClubMass.backendClubMass.model.Usuario;
import java.util.List;

public interface UsuarioService {
    Usuario create(Usuario usuario);
    Usuario update(Usuario usuario);
    Usuario findById(Integer id);
    List<Usuario> findAll();
}
