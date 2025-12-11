package backendClubMass.backendClubMass.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre1", length = 100)
    private String nombre1;

    @Column(name = "nombre2", length = 100)
    private String nombre2;

    @Column(name = "apellido_paterno", length = 100)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 100)
    private String apellidoMaterno;

    @Column(name="username", length = 50, unique = true, nullable = false)
    private String username;

    @Column(name="contrasena", length = 255, nullable = false)
    private String contrasena;

    @Column(name="correo", length = 255, unique = true, nullable = false)
    private String correo;

    @Column(name = "estado", columnDefinition = "TINYINT")
    private Integer estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
    }
}
