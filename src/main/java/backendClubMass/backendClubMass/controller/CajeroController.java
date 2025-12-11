package backendClubMass.backendClubMass.controller;

import backendClubMass.backendClubMass.dto.request.CajeroRequest;
import backendClubMass.backendClubMass.dto.response.CajeroResponse;
import backendClubMass.backendClubMass.service.CajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cajeros")
public class CajeroController {

    @Autowired
    private CajeroService cajeroService;

    @GetMapping
    public ResponseEntity<List<CajeroResponse>> getAllCajeros() {
        return ResponseEntity.ok(cajeroService.getAllCajeros());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<CajeroResponse>> getAllCajerosActivos() {
        return ResponseEntity.ok(cajeroService.getAllCajerosActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CajeroResponse> getCajeroById(@PathVariable Integer id) {
        return ResponseEntity.ok(cajeroService.getCajeroById(id));
    }

    @PostMapping
    public ResponseEntity<CajeroResponse> createCajero(@RequestBody CajeroRequest request) {
        return ResponseEntity.ok(cajeroService.createCajero(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CajeroResponse> updateCajero(@PathVariable Integer id,
                                                       @RequestBody CajeroRequest request) {
        return ResponseEntity.ok(cajeroService.updateCajero(id, request));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<CajeroResponse> cambiarEstado(@PathVariable Integer id,
                                                        @RequestBody CajeroRequest request) {
        return ResponseEntity.ok(cajeroService.cambiarEstadoCajero(id, request.getEstado()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCajero(@PathVariable Integer id) {
        return ResponseEntity.ok(cajeroService.deleteCajero(id));
    }
}
