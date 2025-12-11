package backendClubMass.backendClubMass.service.serviceImpl;

import backendClubMass.backendClubMass.dao.ProductoDAO;
import backendClubMass.backendClubMass.dto.request.ProductoRequest;
import backendClubMass.backendClubMass.dto.response.ProductoResponse;
import backendClubMass.backendClubMass.model.Producto;
import backendClubMass.backendClubMass.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDAO productoDAO;

    @Override
    public ProductoResponse createProducto(ProductoRequest request) {
        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .estado(request.getEstado())
                .imagenUrl(request.getImagenUrl())
                .descripcion(request.getDescripcion())
                .build();
        Producto creado = productoDAO.create(producto);
        return toResponse(creado);
    }

    @Override
    public ProductoResponse updateProducto(Integer id, ProductoRequest request) {
        Producto producto = productoDAO.read(id);
        if (producto == null) throw new RuntimeException("Producto no encontrado");

        if (request.getNombre() != null) producto.setNombre(request.getNombre());
        if (request.getPrecio() != null) producto.setPrecio(request.getPrecio());
        if (request.getStock() != null) producto.setStock(request.getStock());
        if (request.getEstado() != null) producto.setEstado(request.getEstado());
        if (request.getImagenUrl() != null) producto.setImagenUrl(request.getImagenUrl());
        if (request.getDescripcion() != null) producto.setDescripcion(request.getDescripcion());

        Producto actualizado = productoDAO.update(producto);
        return toResponse(actualizado);
    }

    @Override
    public ProductoResponse getProductoById(Integer id) {
        Producto producto = productoDAO.read(id);
        if (producto == null) throw new RuntimeException("Producto no encontrado");
        return toResponse(producto);
    }

    @Override
    public List<ProductoResponse> getAllProductos() {
        return productoDAO.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponse cambiarEstadoProducto(Integer id, Integer estado) {
        Producto producto = productoDAO.read(id);
        if (producto == null) throw new RuntimeException("Producto no encontrado");
        producto.setEstado(estado);
        Producto actualizado = productoDAO.update(producto);
        return toResponse(actualizado);
    }

    @Override
    public ProductoResponse actualizarStock(Integer id, Integer stock) {
        Producto producto = productoDAO.read(id);
        if (producto == null) throw new RuntimeException("Producto no encontrado");
        producto.setStock(stock);
        Producto actualizado = productoDAO.update(producto);
        return toResponse(actualizado);
    }

    private ProductoResponse toResponse(Producto producto) {
        return new ProductoResponse(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getEstado(),
                producto.getImagenUrl(),
                producto.getDescripcion()
        );
    }
}
