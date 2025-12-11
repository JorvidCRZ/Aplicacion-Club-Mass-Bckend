package backendClubMass.backendClubMass.service.serviceImpl;

import backendClubMass.backendClubMass.dao.CajeroDAO;
import backendClubMass.backendClubMass.dao.ClienteDAO;
import backendClubMass.backendClubMass.dao.CompraDAO;
import backendClubMass.backendClubMass.dao.ProductoDAO;
import backendClubMass.backendClubMass.dto.request.CompraRequest;
import backendClubMass.backendClubMass.dto.response.CompraResponse;
import backendClubMass.backendClubMass.dto.response.DetalleCompraResponse;
import backendClubMass.backendClubMass.model.*;
import backendClubMass.backendClubMass.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraDAO compraDAO;

    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private CajeroDAO cajeroDAO;

    @Autowired
    private ProductoDAO productoDAO;

    @Override
    public CompraResponse createCompra(CompraRequest request) {
        Cliente cliente = clienteDAO.read(request.getIdCliente());
        if (cliente == null) throw new RuntimeException("Cliente no encontrado");

        Cajero cajero = cajeroDAO.read(request.getIdCajero());
        if (cajero == null) throw new RuntimeException("Cajero no encontrado");

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setCajero(cajero);
        compra.setMetodoPago(request.getMetodoPago());

        List<DetalleCompra> detalles = request.getDetalles().stream().map(d -> {
            Producto producto = productoDAO.read(d.getIdProducto());
            if (producto == null) throw new RuntimeException("Producto no encontrado: " + d.getIdProducto());

            DetalleCompra detalle = new DetalleCompra();
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(d.getPrecioUnitario());
            detalle.setCompra(compra);
            return detalle;
        }).toList();

        compra.setDetalles(detalles);

        BigDecimal montoTotal = detalles.stream()
                .map(d -> d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        compra.setMontoTotal(montoTotal);

        // Ejemplo simple de puntos generados
        compra.setPuntosGenerados(montoTotal.divide(BigDecimal.valueOf(10)));

        Compra creado = compraDAO.create(compra);
        return toResponse(creado);
    }

    @Override
    public CompraResponse getCompraById(Integer id) {
        Compra compra = compraDAO.read(id);
        if (compra == null) throw new RuntimeException("Compra no encontrada");
        return toResponse(compra);
    }

    @Override
    public List<CompraResponse> getComprasByCliente(Integer idCliente) {
        return compraDAO.findByClienteId(idCliente).stream()
                .map(this::toResponse)
                .toList();
    }

    private CompraResponse toResponse(Compra compra) {
        List<DetalleCompraResponse> detalles = compra.getDetalles().stream()
                .map(d -> new DetalleCompraResponse(
                        d.getIdDetalle(),
                        d.getProducto().getIdProducto(),
                        d.getProducto().getNombre(),
                        d.getCantidad(),
                        d.getPrecioUnitario()
                )).toList();

        return new CompraResponse(
                compra.getIdCompra(),
                compra.getFechaCompra(),
                compra.getMontoTotal(),
                compra.getMetodoPago(),
                compra.getPuntosGenerados(),
                compra.getCliente().getIdCliente(),
                compra.getCliente().getUsuario().getNombre1(),
                compra.getCajero().getIdCajero(),
                compra.getCajero().getUsuario().getNombre1(),
                detalles
        );
    }
}
