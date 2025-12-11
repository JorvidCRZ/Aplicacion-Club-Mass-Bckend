    package backendClubMass.backendClubMass.utils;

    import backendClubMass.backendClubMass.dao.*;
    import backendClubMass.backendClubMass.dao.daoImpl.*;
    import backendClubMass.backendClubMass.model.*;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.stereotype.Component;import org.springframework.context.event.EventListener;
    import org.springframework.boot.context.event.ApplicationReadyEvent;
    import org.springframework.transaction.annotation.Transactional;

    import java.math.BigDecimal;

    @Component
    public class DataInitializer {

        @Autowired
        private RolDAOImpl rolDAO;

        @Autowired
        private UsuarioDAOImpl usuarioDAO;

        @Autowired
        private AdministradorDAOImpl administradorDAO;

        @Autowired
        private CajeroDAOImpl cajeroDAO;

        @Autowired
        private ClienteDAOImpl clienteDAO;

        @Autowired
        private ProductoDAOImpl productoDAO;

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

        @EventListener
        @Transactional
        public void onApplicationReady(ApplicationReadyEvent event) {

            // --- ROLES ---
            Rol rolAdmin = rolDAO.findByNombreRol("ADMIN")
                    .orElseGet(() -> rolDAO.create(Rol.builder().nombreRol("ADMIN").build()));
            Rol rolCajero = rolDAO.findByNombreRol("CAJERO")
                    .orElseGet(() -> rolDAO.create(Rol.builder().nombreRol("CAJERO").build()));
            Rol rolCliente = rolDAO.findByNombreRol("CLIENTE")
                    .orElseGet(() -> rolDAO.create(Rol.builder().nombreRol("CLIENTE").build()));

            // --- USUARIOS ---
            Usuario admin = null;
            if (!usuarioDAO.existsByUsername("admin1")) {
                admin = Usuario.builder()
                        .nombre1("Carlos")
                        .apellidoPaterno("Ramirez")
                        .apellidoMaterno("Gomez")
                        .username("admin1")
                        .contrasena(passwordEncoder.encode("admin123"))
                        .correo("admin@clubmass.com")
                        .rol(rolAdmin)
                        .estado(1)
                        .build();
                usuarioDAO.create(admin);
            } else {
                admin = usuarioDAO.findByUsername("admin1").get();
            }

            Usuario cajero = null;
            if (!usuarioDAO.existsByUsername("cajero1")) {
                cajero = Usuario.builder()
                        .nombre1("Juan")
                        .apellidoPaterno("Lopez")
                        .apellidoMaterno("Perez")
                        .username("cajero1")
                        .contrasena(passwordEncoder.encode("cajero123"))
                        .correo("cajero@mail.com")
                        .rol(rolCajero)
                        .estado(1)
                        .build();
                usuarioDAO.create(cajero);
            } else {
                cajero = usuarioDAO.findByUsername("cajero1").get();
            }

            Usuario clienteUser = null;
            if (!usuarioDAO.existsByUsername("cliente1")) {
                clienteUser = Usuario.builder()
                        .nombre1("Ana")
                        .nombre2("Maria")
                        .apellidoPaterno("Torres")
                        .apellidoMaterno("Diaz")
                        .username("cliente1")
                        .contrasena(passwordEncoder.encode("cliente123"))
                        .correo("ana@mail.com")
                        .rol(rolCliente)
                        .estado(1)
                        .build();
                usuarioDAO.create(clienteUser);
            } else {
                clienteUser = usuarioDAO.findByUsername("cliente1").get();
            }

            // --- TABLAS ESPECÍFICAS ---
            if (!administradorDAO.existsByUsuario(admin)) {
                Administrador adminEntity = new Administrador();
                adminEntity.setUsuario(admin);
                administradorDAO.create(adminEntity);

            }

            if (!cajeroDAO.existsByUsuario(cajero)) {
                Cajero caj = new Cajero();
                caj.setUsuario(cajero);
                caj.setEstado("ACTIVO");
                cajeroDAO.create(caj);
            }


            if (!clienteDAO.existsByUsuario(clienteUser)) {
                Cliente cl = new Cliente();
                cl.setUsuario(clienteUser);
                cl.setTelefono("987654321");
                cl.setDni("12345678");
                cl.setCodigoMembresia("MEM001");
                clienteDAO.create(cl);
            }

            // --- PRODUCTOS ---
            if (productoDAO.findAll().isEmpty()) {
                productoDAO.create(new Producto(null, "Proteína Whey 1KG", new BigDecimal("129.90"), 40, null, "nada", null));
                productoDAO.create(new Producto(null, "Guantes de entrenamiento", new BigDecimal("25.90"), 60, null, "nada", null));
                productoDAO.create(new Producto(null, "Toalla deportiva", new BigDecimal("15.90"), 120, null, "nada", null));
                productoDAO.create(new Producto(null, "Mochila Gym", new BigDecimal("89.90"), 30, null, "nada", null));
            }
        }
    }
