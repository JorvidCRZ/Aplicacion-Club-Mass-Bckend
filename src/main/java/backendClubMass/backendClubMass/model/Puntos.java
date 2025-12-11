package backendClubMass.backendClubMass.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "puntos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Puntos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_puntos")
    private Integer idPuntos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_campana")
    private Campana campana;

    @Column(name = "puntos_ganados")
    private BigDecimal puntosGanados;

    @Column(name = "puntos_canjeados")
    private BigDecimal puntosCanjeados;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
    }
}