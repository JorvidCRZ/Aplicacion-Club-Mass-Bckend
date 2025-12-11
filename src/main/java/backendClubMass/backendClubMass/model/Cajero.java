package backendClubMass.backendClubMass.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cajero")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cajero")
    private Integer idCajero;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", unique = true, nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "estado",length = 20)
    private String estado;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
        if (estado == null) {
            estado = "ACTIVO";
        }
    }
}