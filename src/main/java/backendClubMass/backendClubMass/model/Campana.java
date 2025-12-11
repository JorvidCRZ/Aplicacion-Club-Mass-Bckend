package backendClubMass.backendClubMass.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "campana")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Campana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campana")
    private Integer idCampana;

    @Column(name = "nombre_campana",length = 150)
    private String nombreCampana;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "multiplicador_puntos")
    private BigDecimal multiplicadorPuntos;

    @Column(name = "estado",columnDefinition = "TINYINT")
    private Integer estado;;
}
