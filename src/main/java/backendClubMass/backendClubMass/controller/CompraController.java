package backendClubMass.backendClubMass.controller;

import backendClubMass.backendClubMass.dto.request.CompraRequest;
import backendClubMass.backendClubMass.dto.response.CompraResponse;
import backendClubMass.backendClubMass.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraResponse> createCompra(@RequestBody CompraRequest request) {
        return ResponseEntity.ok(compraService.createCompra(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> getCompraById(@PathVariable Integer id) {
        return ResponseEntity.ok(compraService.getCompraById(id));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<CompraResponse>> getComprasByCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(compraService.getComprasByCliente(idCliente));
    }
}
