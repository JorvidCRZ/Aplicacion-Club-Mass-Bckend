package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.ProductoDAO;
import backendClubMass.backendClubMass.model.Producto;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public Producto create(Producto p) {
        String sql = "INSERT INTO producto (nombre, precio, stock, imagenUrl, estado) VALUES (?,?,?,?,?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setBigDecimal(2, p.getPrecio() == null ? null : java.math.BigDecimal.valueOf(p.getPrecio()));
            ps.setInt(3, p.getStock() == null ? 0 : p.getStock());
            ps.setString(4, p.getImagenUrl());
            ps.setInt(5, p.getEstado() == null ? 1 : p.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setIdProducto(rs.getInt(1));
            }
            return read(p.getIdProducto());
        } catch (SQLException e) {
            throw new RuntimeException("Error creando producto", e);
        }
    }

    @Override
    public Producto read(Integer id) {
        String sql = "SELECT * FROM producto WHERE idProducto = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto p = new Producto();
                    p.setIdProducto(rs.getInt("idProducto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getBigDecimal("precio") != null ? rs.getBigDecimal("precio").doubleValue() : null);
                    p.setStock(rs.getInt("stock"));
                    p.setImagenUrl(rs.getString("imagenUrl"));
                    p.setEstado(rs.getInt("estado"));
                    return p;
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo producto", e);
        }
    }

    @Override
    public Producto update(Producto p) {
        String sql = "UPDATE producto SET nombre=?, precio=?, stock=?, imagenUrl=?, estado=? WHERE idProducto=?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setBigDecimal(2, p.getPrecio() == null ? null : java.math.BigDecimal.valueOf(p.getPrecio()));
            ps.setInt(3, p.getStock() == null ? 0 : p.getStock());
            ps.setString(4, p.getImagenUrl());
            ps.setInt(5, p.getEstado() == null ? 1 : p.getEstado());
            ps.setInt(6, p.getIdProducto());
            int affected = ps.executeUpdate();
            return affected > 0 ? read(p.getIdProducto()) : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando producto", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM producto WHERE idProducto = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando producto", e);
        }
    }

    @Override
    public List<Producto> findAll() {
        String sql = "SELECT * FROM producto ORDER BY idProducto";
        List<Producto> list = new ArrayList<>();
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getBigDecimal("precio") != null ? rs.getBigDecimal("precio").doubleValue() : null);
                p.setStock(rs.getInt("stock"));
                p.setImagenUrl(rs.getString("imagenUrl"));
                p.setEstado(rs.getInt("estado"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error listando productos", e);
        }
    }
}
