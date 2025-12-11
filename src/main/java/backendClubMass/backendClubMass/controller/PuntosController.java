package backendClubMass.backendClubMass.controller;

import backendClubMass.backendClubMass.dto.response.PuntosResponse;
import backendClubMass.backendClubMass.model.Puntos;
import backendClubMass.backendClubMass.service.PuntosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/puntos")
public class PuntosController {

    @Autowired
    private PuntosService puntosService;

    @GetMapping("/{idCliente}/disponibles")
    public ResponseEntity<BigDecimal> obtenerPuntosDisponibles(@PathVariable Integer idCliente) {
        BigDecimal puntosDisponibles = puntosService.obtenerPuntosDisponibles(idCliente);
        return ResponseEntity.ok(puntosDisponibles);
    }

    @GetMapping("/{idCliente}/historial")
    public ResponseEntity<List<PuntosResponse>> obtenerHistorialPuntos(@PathVariable Integer idCliente) {
        List<PuntosResponse> historial = puntosService.obtenerHistorialPuntos(idCliente);
        return ResponseEntity.ok(historial);
    }


}
