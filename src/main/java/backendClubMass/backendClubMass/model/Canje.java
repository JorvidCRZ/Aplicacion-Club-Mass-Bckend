package backendClubMass.backendClubMass.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "canje")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Canje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_canje")
    private Integer idCanje;

    @Column(name = "fecha_canje")
    private LocalDateTime fechaCanje;

    @Column(name = "puntos_usados")
    private BigDecimal puntosUsados;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cajero")
    private Cajero cajero;

    @OneToMany(mappedBy = "canje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCanje> detalles;

    @PrePersist
    public void prePersist() {
        fechaCanje = LocalDateTime.now();
    }
}