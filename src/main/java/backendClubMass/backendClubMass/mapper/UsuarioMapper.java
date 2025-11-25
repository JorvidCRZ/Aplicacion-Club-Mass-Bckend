package backendClubMass.backendClubMass.mapper;

import backendClubMass.backendClubMass.dto.request.UsuarioRequestDTO;
import backendClubMass.backendClubMass.dto.response.UsuarioResponseDTO;
import backendClubMass.backendClubMass.model.Rol;
import backendClubMass.backendClubMass.model.Usuario;

public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto, Rol rol) {
        Usuario usuario = new Usuario();
        usuario.setNombre1(dto.getNombre1());
        usuario.setNombre2(dto.getNombre2());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setCorreo(dto.getCorreo());
        usuario.setRol(rol);
        usuario.setEstado(1);
        return usuario;
    }

    public UsuarioResponseDTO toDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre1(usuario.getNombre1());
        dto.setNombre2(usuario.getNombre2());
        dto.setApellidoPaterno(usuario.getApellidoPaterno());
        dto.setApellidoMaterno(usuario.getApellidoMaterno());
        dto.setUsername(usuario.getUsername());
        dto.setCorreo(usuario.getCorreo());
        dto.setEstado(usuario.getEstado());

        if (usuario.getRol() != null) {
            dto.setRol(usuario.getRol());
        }

        return dto;
    }
}