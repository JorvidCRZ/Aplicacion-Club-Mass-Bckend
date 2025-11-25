package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.UsuarioDAO;
import backendClubMass.backendClubMass.model.Rol;
import backendClubMass.backendClubMass.model.Usuario;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public Usuario create(Usuario u) {
        String sql = "INSERT INTO usuario (nombre1, nombre2, apellidoPaterno, apellidoMaterno, username, password, correo, rol_id, estado) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getNombre1());
            ps.setString(2, u.getNombre2());
            ps.setString(3, u.getApellidoPaterno());
            ps.setString(4, u.getApellidoMaterno());
            ps.setString(5, u.getUsername());
            ps.setString(6, u.getPassword());
            ps.setString(7, u.getCorreo());
            ps.setInt(8, u.getRol() != null ? u.getRol().getIdRol() : 0);
            ps.setInt(9, u.getEstado() != null ? u.getEstado() : 1);

            int affected = ps.executeUpdate();
            if (affected == 0) throw new RuntimeException("No se creó el usuario");

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) u.setIdUsuario(rs.getInt(1));
            }
            return read(u.getIdUsuario());
        } catch (SQLException e) {
            throw new RuntimeException("Error creando usuario", e);
        }
    }

    @Override
    public Usuario read(Integer id) {
        String sql = "SELECT u.*, r.idRol AS r_id, r.nombreRol AS r_nombre FROM usuario u JOIN roles r ON u.rol_id = r.idRol WHERE u.idUsuario = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo usuario", e);
        }
    }

    @Override
    public Usuario update(Usuario u) {
        String sql = "UPDATE usuario SET nombre1=?, nombre2=?, apellidoPaterno=?, apellidoMaterno=?, username=?, password=?, correo=?, rol_id=?, estado=? WHERE idUsuario=?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNombre1());
            ps.setString(2, u.getNombre2());
            ps.setString(3, u.getApellidoPaterno());
            ps.setString(4, u.getApellidoMaterno());
            ps.setString(5, u.getUsername());
            ps.setString(6, u.getPassword());
            ps.setString(7, u.getCorreo());
            ps.setInt(8, u.getRol() != null ? u.getRol().getIdRol() : 0);
            ps.setInt(9, u.getEstado() != null ? u.getEstado() : 1);
            ps.setInt(10, u.getIdUsuario());

            int affected = ps.executeUpdate();
            return affected > 0 ? read(u.getIdUsuario()) : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando usuario", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando usuario", e);
        }
    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT u.*, r.idRol AS r_id, r.nombreRol AS r_nombre FROM usuario u JOIN roles r ON u.rol_id = r.idRol ORDER BY u.idUsuario";
        List<Usuario> list = new ArrayList<>();
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error listando usuarios", e);
        }
    }

    @Override
    public Usuario findByUsername(String username) {
        String sql = "SELECT u.*, r.idRol AS r_id, r.nombreRol AS r_nombre FROM usuario u JOIN roles r ON u.rol_id = r.idRol WHERE u.username = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando usuario por username", e);
        }
    }

    private Usuario mapRow(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("idUsuario"));
        u.setNombre1(rs.getString("nombre1"));
        u.setNombre2(rs.getString("nombre2"));
        u.setApellidoPaterno(rs.getString("apellidoPaterno"));
        u.setApellidoMaterno(rs.getString("apellidoMaterno"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setCorreo(rs.getString("correo"));
        u.setEstado(rs.getInt("estado"));

        Rol r = new Rol();
        r.setIdRol(rs.getInt("r_id"));
        r.setNombreRol(rs.getString("r_nombre"));

        u.setRol(r);

        return u;
    }
}