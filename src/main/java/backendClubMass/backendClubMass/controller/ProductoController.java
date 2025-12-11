package backendClubMass.backendClubMass.controller;

import backendClubMass.backendClubMass.dto.request.ProductoRequest;
import backendClubMass.backendClubMass.dto.response.ProductoResponse;
import backendClubMass.backendClubMass.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> getAllProductos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> createProducto(@RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoService.createProducto(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> updateProducto(@PathVariable Integer id,
                                                           @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoService.updateProducto(id, request));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ProductoResponse> cambiarEstado(@PathVariable Integer id,
                                                          @RequestParam Integer estado) {
        return ResponseEntity.ok(productoService.cambiarEstadoProducto(id, estado));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponse> actualizarStock(@PathVariable Integer id,
                                                            @RequestParam Integer stock) {
        return ResponseEntity.ok(productoService.actualizarStock(id, stock));
    }
}
