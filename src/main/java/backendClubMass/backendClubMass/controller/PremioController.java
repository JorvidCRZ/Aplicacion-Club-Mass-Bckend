package backendClubMass.backendClubMass.controller;

import backendClubMass.backendClubMass.dto.request.PremioRequest;
import backendClubMass.backendClubMass.dto.response.PremioResponse;
import backendClubMass.backendClubMass.service.PremioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/premios")
@RequiredArgsConstructor
public class PremioController {

    private final PremioService premioService;

    @GetMapping
    public ResponseEntity<List<PremioResponse>> getAll() {
        return ResponseEntity.ok(premioService.findAll());
    }

    @PostMapping
    public ResponseEntity<PremioResponse> create(@RequestBody PremioRequest request) {
        return ResponseEntity.ok(premioService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PremioResponse> update(
            @PathVariable Integer id,
            @RequestBody PremioRequest request
    ) {
        return ResponseEntity.ok(premioService.update(id, request));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<PremioResponse> updateStock(
            @PathVariable Integer id,
            @RequestParam Integer stock
    ) {
        return ResponseEntity.ok(premioService.updateStock(id, stock));
    }
}
