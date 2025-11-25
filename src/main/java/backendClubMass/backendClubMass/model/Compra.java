package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

    private Integer idCompra;
    private LocalDateTime fechaCompra;
    private Double montoTotal;
    private String metodoPago;
    private Double puntosGenerados;

    private Cliente cliente;
    private Cajero cajero;

    private List<DetalleCompra> detalles;
}