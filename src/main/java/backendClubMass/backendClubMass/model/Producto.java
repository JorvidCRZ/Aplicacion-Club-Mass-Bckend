package backendClubMass.backendClubMass.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Integer idProducto;
    private String nombre;
    private Double precio;
    private Integer stock;
    private String imagenUrl;
    private Integer estado;
}