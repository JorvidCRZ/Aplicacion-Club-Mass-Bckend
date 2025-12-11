package backendClubMass.backendClubMass.controller;

import backendClubMass.backendClubMass.dto.request.AdminRequest;
import backendClubMass.backendClubMass.dto.response.AdminResponse;
import backendClubMass.backendClubMass.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        return ResponseEntity.ok(administradorService.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> getAdminById(@PathVariable Integer id) {
        return ResponseEntity.ok(administradorService.getAdminById(id));
    }

    @PostMapping
    public ResponseEntity<AdminResponse> createAdmin(@RequestBody AdminRequest request) {
        return ResponseEntity.ok(administradorService.createAdmin(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponse> updateAdmin(@PathVariable Integer id,
                                                     @RequestBody AdminRequest request) {
        return ResponseEntity.ok(administradorService.updateAdmin(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAdmin(@PathVariable Integer id) {
        return ResponseEntity.ok(administradorService.deleteAdmin(id));
    }
}