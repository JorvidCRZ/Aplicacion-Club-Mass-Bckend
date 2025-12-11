package backendClubMass.backendClubMass.service;

import backendClubMass.backendClubMass.dto.request.ProductoRequest;
import backendClubMass.backendClubMass.dto.response.ProductoResponse;
import java.util.List;

public interface ProductoService {
    ProductoResponse createProducto(ProductoRequest request);
    ProductoResponse updateProducto(Integer id, ProductoRequest request);
    ProductoResponse getProductoById(Integer id);
    List<ProductoResponse> getAllProductos();
    ProductoResponse cambiarEstadoProducto(Integer id, Integer estado);
    ProductoResponse actualizarStock(Integer id, Integer stock);
}
