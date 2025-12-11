package backendClubMass.backendClubMass.controller;

import backendClubMass.backendClubMass.dao.ClienteDAO;
import backendClubMass.backendClubMass.dto.request.ClienteRequest;
import backendClubMass.backendClubMass.dto.response.ClienteResponse;
import backendClubMass.backendClubMass.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteDAO clienteDAO;

    @PostMapping
    public ResponseEntity<ClienteResponse> createCliente(@RequestBody ClienteRequest request) {
        return ResponseEntity.ok(clienteService.createCliente(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> updateCliente(@PathVariable Integer id, @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(clienteService.updateCliente(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> getClienteById(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ClienteResponse> buscarCliente(@RequestParam(required = false) String dni,
                                                         @RequestParam(required = false) String codigo) {
        if (dni != null) return ResponseEntity.ok(clienteService.getClienteByDni(dni));
        if (codigo != null) return ResponseEntity.ok(clienteService.getClienteByCodigo(codigo));
        throw new RuntimeException("Debe enviar dni o código");
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
