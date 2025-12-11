package backendClubMass.backendClubMass.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_canje")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleCanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_canje")
    private Integer idDetalleCanje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_canje")
    private Canje canje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_premio")
    private Premio premio;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "puntos_por_unidad")
    private Integer puntosPorUnidad;

    @Column(name = "subtotal_puntos")
    private Integer subtotalPuntos;
}