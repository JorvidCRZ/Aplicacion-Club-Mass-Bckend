package backendClubMass.backendClubMass.service;

import backendClubMass.backendClubMass.dto.request.UsuarioRequest;
import backendClubMass.backendClubMass.dto.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> getAllUsuarios();
    UsuarioResponse getUsuarioById(Integer id);
    UsuarioResponse createUsuario(UsuarioRequest request);
    UsuarioResponse updateUsuario(Integer id, UsuarioRequest request);
    UsuarioResponse cambiarEstadoUsuario(Integer id, Integer estado);
    boolean deleteUsuario(Integer id);
}
