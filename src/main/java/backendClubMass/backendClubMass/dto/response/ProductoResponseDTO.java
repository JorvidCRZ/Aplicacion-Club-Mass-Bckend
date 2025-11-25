package backendClubMass.backendClubMass.dto.response;

import lombok.Data;

@Data
public class ProductoResponseDTO {

    private Integer idProducto;
    private String nombre;
    private Double precio;
    private Integer stock;
    private String imagenUrl;
    private Integer estado;
}