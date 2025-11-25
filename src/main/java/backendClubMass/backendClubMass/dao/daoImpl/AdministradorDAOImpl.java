package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.AdministradorDAO;
import backendClubMass.backendClubMass.model.Administrador;
import backendClubMass.backendClubMass.model.Usuario;
import backendClubMass.backendClubMass.model.Rol;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAOImpl implements AdministradorDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public Administrador create(Administrador a) {
        String sql = "INSERT INTO administrador (idUsuario) VALUES (?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, a.getUsuario().getIdUsuario());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) a.setIdAdmin(rs.getInt(1));
            }

            return read(a.getIdAdmin());

        } catch (SQLException e) {
            throw new RuntimeException("Error creando administrador", e);
        }
    }

    @Override
    public Administrador read(Integer id) {
        String sql = """
                SELECT ad.idAdmin,
                       u.idUsuario, u.nombre1, u.nombre2, u.apellidoPaterno, u.apellidoMaterno,
                       u.username, u.correo, u.estado,
                       r.idRol AS r_id, r.nombreRol AS r_nombreRol
                FROM administrador ad
                JOIN usuario u ON ad.idUsuario = u.idUsuario
                JOIN roles r ON u.rol_id = r.idRol
                WHERE ad.idAdmin = ?
                """;

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    Administrador a = new Administrador();
                    a.setIdAdmin(rs.getInt("idAdmin"));

                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("idUsuario"));
                    u.setNombre1(rs.getString("nombre1"));
                    u.setNombre2(rs.getString("nombre2"));
                    u.setApellidoPaterno(rs.getString("apellidoPaterno"));
                    u.setApellidoMaterno(rs.getString("apellidoMaterno"));
                    u.setUsername(rs.getString("username"));
                    u.setCorreo(rs.getString("correo"));
                    u.setEstado(rs.getInt("estado"));

                    Rol r = new Rol();
                    r.setIdRol(rs.getInt("r_id"));
                    r.setNombreRol(rs.getString("r_nombreRol"));
                    u.setRol(r);

                    a.setUsuario(u);
                    return a;
                }
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo administrador", e);
        }
    }

    @Override
    public Administrador update(Administrador a) {
        String sql = "UPDATE administrador SET idUsuario = ? WHERE idAdmin = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getUsuario().getIdUsuario());
            ps.setInt(2, a.getIdAdmin());

            int affected = ps.executeUpdate();
            return affected > 0 ? read(a.getIdAdmin()) : null;

        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando administrador", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM administrador WHERE idAdmin = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando administrador", e);
        }
    }

    @Override
    public List<Administrador> findAll() {

        String sql = """
                SELECT ad.idAdmin,
                       u.idUsuario, u.nombre1, u.nombre2, u.apellidoPaterno, u.apellidoMaterno,
                       u.username, u.correo, u.estado,
                       r.idRol AS r_id, r.nombreRol AS r_nombreRol
                FROM administrador ad
                JOIN usuario u ON ad.idUsuario = u.idUsuario
                JOIN roles r ON u.rol_id = r.idRol
                ORDER BY ad.idAdmin
                """;

        List<Administrador> list = new ArrayList<>();

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Administrador a = new Administrador();
                a.setIdAdmin(rs.getInt("idAdmin"));

                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre1(rs.getString("nombre1"));
                u.setNombre2(rs.getString("nombre2"));
                u.setApellidoPaterno(rs.getString("apellidoPaterno"));
                u.setApellidoMaterno(rs.getString("apellidoMaterno"));
                u.setUsername(rs.getString("username"));
                u.setCorreo(rs.getString("correo"));
                u.setEstado(rs.getInt("estado"));

                Rol r = new Rol();
                r.setIdRol(rs.getInt("r_id"));
                r.setNombreRol(rs.getString("r_nombreRol"));
                u.setRol(r);

                a.setUsuario(u);
                list.add(a);
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Error listando administradores", e);
        }
    }
}
