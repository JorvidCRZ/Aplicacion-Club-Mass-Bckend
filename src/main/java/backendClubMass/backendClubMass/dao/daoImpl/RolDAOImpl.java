package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.RolDAO;
import backendClubMass.backendClubMass.model.Rol;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAOImpl implements RolDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public Rol create(Rol rol) {
        String sql = "INSERT INTO roles (nombreRol) VALUES (?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, rol.getNombreRol());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) rol.setIdRol(rs.getInt(1));
            }
            return rol;
        } catch (SQLException e) {
            throw new RuntimeException("Error creando rol", e);
        }
    }

    @Override
    public Rol read(Integer id) {
        String sql = "SELECT * FROM roles WHERE idRol = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Rol r = new Rol();
                    r.setIdRol(rs.getInt("idRol"));
                    r.setNombreRol(rs.getString("nombreRol"));
                    return r;
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo rol", e);
        }
    }

    @Override
    public Rol update(Rol rol) {
        String sql = "UPDATE roles SET nombreRol = ? WHERE idRol = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rol.getNombreRol());
            ps.setInt(2, rol.getIdRol());
            int affected = ps.executeUpdate();
            return affected > 0 ? rol : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando rol", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM roles WHERE idRol = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando rol", e);
        }
    }

    @Override
    public List<Rol> findAll() {
        String sql = "SELECT * FROM roles ORDER BY idRol";
        List<Rol> list = new ArrayList<>();
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Rol r = new Rol();
                r.setIdRol(rs.getInt("idRol"));
                r.setNombreRol(rs.getString("nombreRol"));
                list.add(r);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error listando roles", e);
        }
    }
}

