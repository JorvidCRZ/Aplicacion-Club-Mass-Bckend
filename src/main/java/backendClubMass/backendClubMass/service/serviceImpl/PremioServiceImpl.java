package backendClubMass.backendClubMass.service.serviceImpl;

import backendClubMass.backendClubMass.dao.PremioDAO;
import backendClubMass.backendClubMass.dto.request.PremioRequest;
import backendClubMass.backendClubMass.dto.response.PremioResponse;
import backendClubMass.backendClubMass.model.Premio;
import backendClubMass.backendClubMass.service.PremioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PremioServiceImpl implements PremioService {

    private final PremioDAO premioDAO;

    private PremioResponse toResponse(Premio p) {
        return PremioResponse.builder()
                .idPremio(p.getIdPremio())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .puntosRequeridos(p.getPuntosRequeridos())
                .stock(p.getStock())
                .imagen(p.getImagen())
                .build();
    }

    @Override
    public List<PremioResponse> findAll() {
        return premioDAO.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PremioResponse create(PremioRequest r) {
        Premio p = Premio.builder()
                .nombre(r.getNombre())
                .descripcion(r.getDescripcion())
                .puntosRequeridos(r.getPuntosRequeridos())
                .stock(r.getStock())
                .imagen(r.getImagen())
                .build();

        premioDAO.create(p);
        return toResponse(p);
    }

    @Override
    @Transactional
    public PremioResponse update(Integer id, PremioRequest r) {
        Premio p = premioDAO.read(id);
        if (p == null) return null;

        p.setNombre(r.getNombre());
        p.setDescripcion(r.getDescripcion());
        p.setPuntosRequeridos(r.getPuntosRequeridos());
        p.setStock(r.getStock());
        p.setImagen(r.getImagen());

        premioDAO.update(p);
        return toResponse(p);
    }

    @Override
    @Transactional
    public PremioResponse updateStock(Integer id, Integer nuevoStock) {
        Premio p = premioDAO.read(id);
        if (p == null) return null;

        p.setStock(nuevoStock);
        premioDAO.update(p);

        return toResponse(p);
    }
}
