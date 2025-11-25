package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.PremiosDAO;
import backendClubMass.backendClubMass.model.Premio;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PremioDAOImpl implements PremiosDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public Premio create(Premio p) {
        String sql = "INSERT INTO premios (nombre, descripcion, puntosRequeridos, stock, imagen) VALUES (?,?,?,?,?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getPuntosRequeridos());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getImagen());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setIdPremio(rs.getInt(1));
            }
            return read(p.getIdPremio());
        } catch (SQLException e) {
            throw new RuntimeException("Error creando premio", e);
        }
    }

    @Override
    public Premio read(Integer id) {
        String sql = "SELECT * FROM premios WHERE idPremio = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Premio p = new Premio();
                    p.setIdPremio(rs.getInt("idPremio"));
                    p.setNombre(rs.getString("nombre"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setPuntosRequeridos(rs.getInt("puntosRequeridos"));
                    p.setStock(rs.getInt("stock"));
                    p.setImagen(rs.getString("imagen"));
                    return p;
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo premio", e);
        }
    }

    @Override
    public Premio update(Premio p) {
        String sql = "UPDATE premios SET nombre=?, descripcion=?, puntosRequeridos=?, stock=?, imagen=? WHERE idPremio=?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getPuntosRequeridos());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getImagen());
            ps.setInt(6, p.getIdPremio());
            int affected = ps.executeUpdate();
            return affected > 0 ? read(p.getIdPremio()) : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando premio", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM premios WHERE idPremio = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando premio", e);
        }
    }

    @Override
    public List<Premio> findAll() {
        String sql = "SELECT * FROM premios ORDER BY idPremio";
        List<Premio> list = new ArrayList<>();
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Premio p = new Premio();
                p.setIdPremio(rs.getInt("idPremio"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPuntosRequeridos(rs.getInt("puntosRequeridos"));
                p.setStock(rs.getInt("stock"));
                p.setImagen(rs.getString("imagen"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error listando premios", e);
        }
    }
}
