package backendClubMass.backendClubMass.dto.request;

import lombok.Data;

@Data
public class ProductoRequestDTO {

    private String nombre;
    private Double precio;
    private Integer stock;
    private String imagenUrl;
}
