package backendClubMass.backendClubMass.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "premios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_premio")
    private Integer idPremio;

    @Column(name = "nombre", length = 150)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "puntos_requeridos")
    private Integer puntosRequeridos;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "imagen", length = 255)
    private String imagen;
}