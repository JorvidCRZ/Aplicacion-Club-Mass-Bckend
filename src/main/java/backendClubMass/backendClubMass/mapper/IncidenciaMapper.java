package backendClubMass.backendClubMass.mapper;

import backendClubMass.backendClubMass.dto.response.IncidenciaResponseDTO;
import backendClubMass.backendClubMass.model.Incidencia;

public class IncidenciaMapper {

    public IncidenciaResponseDTO toDTO(Incidencia incidencia) {

        IncidenciaResponseDTO dto = new IncidenciaResponseDTO();

        dto.setIdIncidencia(incidencia.getIdIncidencia());
        dto.setDescripcion(incidencia.getDescripcion());
        dto.setEstado(incidencia.getEstado());
        dto.setFecha(incidencia.getFecha());

        dto.setIdCliente(incidencia.getCliente().getIdCliente());
        dto.setNombreCliente(incidencia.getCliente().getUsuario().getNombre1());
        dto.setApellidoCliente(incidencia.getCliente().getUsuario().getApellidoPaterno());

        return dto;
    }
}
