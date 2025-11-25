package backendClubMass.backendClubMass.mapper;

import backendClubMass.backendClubMass.dto.response.CompraResponseDTO;
import backendClubMass.backendClubMass.dto.response.DetalleCompraResponseDTO;
import backendClubMass.backendClubMass.model.Compra;
import backendClubMass.backendClubMass.model.DetalleCompra;

import java.util.stream.Collectors;

public class CompraMapper {

    public CompraResponseDTO toDTO(Compra compra) {

        CompraResponseDTO dto = new CompraResponseDTO();
        dto.setIdCompra(compra.getIdCompra());
        dto.setFechaCompra(compra.getFechaCompra());
        dto.setMontoTotal(compra.getMontoTotal());
        dto.setMetodoPago(compra.getMetodoPago());

        // Cliente
        dto.setIdCliente(compra.getCliente().getIdCliente());
        dto.setNombreCliente(compra.getCliente().getUsuario().getNombre1());

        // Cajero
        dto.setIdCajero(compra.getCajero().getIdCajero());
        dto.setNombreCajero(compra.getCajero().getUsuario().getNombre1());

        // Detalles
        dto.setDetalles(
                compra.getDetalles().stream().map(this::toDetalleDTO).collect(Collectors.toList())
        );

        return dto;
    }

    private DetalleCompraResponseDTO toDetalleDTO(DetalleCompra detalle) {
        DetalleCompraResponseDTO dto = new DetalleCompraResponseDTO();
        dto.setIdDetalle(detalle.getIdDetalle());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        dto.setNombreProducto(detalle.getProducto().getNombre());
        return dto;
    }
}
