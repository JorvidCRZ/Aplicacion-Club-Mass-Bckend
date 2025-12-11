package backendClubMass.backendClubMass.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "telefono",length = 20)
    private String telefono;

    @Column(name = "dni",length = 20 ,unique = true)
    private String dni;

    @Column(name = "codigo_membresia",length = 50, unique = true)
    private String codigoMembresia;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", unique = true, nullable = false)
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
    }
}