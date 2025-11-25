package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.DetalleCompraDAO;
import backendClubMass.backendClubMass.model.DetalleCompra;
import backendClubMass.backendClubMass.model.Producto;
import backendClubMass.backendClubMass.model.Compra;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleCompraDAOImpl implements DetalleCompraDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public DetalleCompra create(DetalleCompra d) {
        String sql = "INSERT INTO detalle_compra (cantidad, precioUnitario, idCompra, idProducto) VALUES (?,?,?,?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, d.getCantidad());
            ps.setDouble(2, d.getPrecioUnitario());
            ps.setInt(3, d.getCompra().getIdCompra());
            ps.setInt(4, d.getProducto().getIdProducto());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) d.setIdDetalle(rs.getInt(1));
            }
            return read(d.getIdDetalle());
        } catch (SQLException e) {
            throw new RuntimeException("Error creando detalle_compra", e);
        }
    }

    @Override
    public DetalleCompra read(Integer id) {
        String sql = "SELECT d.*, p.* FROM detalle_compra d JOIN producto p ON d.idProducto = p.idProducto WHERE d.idDetalle = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DetalleCompra dc = new DetalleCompra();
                    dc.setIdDetalle(rs.getInt("idDetalle"));
                    dc.setCantidad(rs.getInt("cantidad"));
                    dc.setPrecioUnitario(rs.getDouble("precioUnitario"));
                    Producto p = new Producto();
                    p.setIdProducto(rs.getInt("idProducto"));
                    p.setNombre(rs.getString("nombre"));
                    dc.setProducto(p);
                    return dc;
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo detalle_compra", e);
        }
    }

    @Override
    public DetalleCompra update(DetalleCompra d) {
        String sql = "UPDATE detalle_compra SET cantidad=?, precioUnitario=?, idCompra=?, idProducto=? WHERE idDetalle=?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getCantidad());
            ps.setDouble(2, d.getPrecioUnitario());
            ps.setInt(3, d.getCompra().getIdCompra());
            ps.setInt(4, d.getProducto().getIdProducto());
            ps.setInt(5, d.getIdDetalle());
            int affected = ps.executeUpdate();
            return affected > 0 ? read(d.getIdDetalle()) : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando detalle_compra", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM detalle_compra WHERE idDetalle = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando detalle_compra", e);
        }
    }

    @Override
    public List<DetalleCompra> findAll() {
        String sql = "SELECT d.*, p.* FROM detalle_compra d JOIN producto p ON d.idProducto = p.idProducto ORDER BY d.idDetalle";
        List<DetalleCompra> list = new ArrayList<>();
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DetalleCompra dc = new DetalleCompra();
                dc.setIdDetalle(rs.getInt("idDetalle"));
                dc.setCantidad(rs.getInt("cantidad"));
                dc.setPrecioUnitario(rs.getDouble("precioUnitario"));
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombre(rs.getString("nombre"));
                dc.setProducto(p);
                list.add(dc);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error listando detalle_compra", e);
        }
    }
}
