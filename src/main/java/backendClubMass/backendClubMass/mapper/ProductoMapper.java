package backendClubMass.backendClubMass.mapper;

import backendClubMass.backendClubMass.dto.request.ProductoRequestDTO;
import backendClubMass.backendClubMass.dto.response.ProductoResponseDTO;
import backendClubMass.backendClubMass.model.Producto;

public class ProductoMapper {

    public static Producto toEntity(ProductoRequestDTO dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        p.setImagenUrl(dto.getImagenUrl());
        return p;
    }

    public static ProductoResponseDTO toDTO(Producto p) {
        ProductoResponseDTO dto = new ProductoResponseDTO();

        dto.setIdProducto(p.getIdProducto());
        dto.setNombre(p.getNombre());
        dto.setPrecio(p.getPrecio());
        dto.setStock(p.getStock());
        dto.setImagenUrl(p.getImagenUrl());
        dto.setEstado(p.getEstado());

        return dto;
    }
}
