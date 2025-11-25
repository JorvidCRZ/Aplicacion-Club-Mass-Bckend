package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCompra {

    private Integer idDetalle;
    private Integer cantidad;
    private Double precioUnitario;

    private Compra compra;
    private Producto producto;
}