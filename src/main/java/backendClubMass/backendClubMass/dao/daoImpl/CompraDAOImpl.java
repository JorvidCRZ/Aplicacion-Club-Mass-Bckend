package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.CompraDAO;
import backendClubMass.backendClubMass.model.Compra;
import backendClubMass.backendClubMass.model.Cajero;
import backendClubMass.backendClubMass.model.Cliente;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompraDAOImpl implements CompraDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public Compra create(Compra comp) {
        String sql = "INSERT INTO compra (montoTotal, metodoPago, puntosGenerados, idCliente, idCajero) VALUES (?,?,?,?,?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, comp.getMontoTotal());
            ps.setString(2, comp.getMetodoPago());
            ps.setDouble(3, comp.getPuntosGenerados());
            ps.setInt(4, comp.getCliente().getIdCliente());
            ps.setInt(5, comp.getCajero().getIdCajero());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) comp.setIdCompra(rs.getInt(1));
            }
            return read(comp.getIdCompra());
        } catch (SQLException e) {
            throw new RuntimeException("Error creando compra", e);
        }
    }

    @Override
    public Compra read(Integer id) {
        String sql = "SELECT co.*, cl.idCliente AS cl_id, ca.idCajero AS ca_id FROM compra co " +
                "JOIN cliente cl ON co.idCliente = cl.idCliente " +
                "JOIN cajero ca ON co.idCajero = ca.idCajero " +
                "WHERE co.idCompra = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Compra c = new Compra();
                    c.setIdCompra(rs.getInt("idCompra"));
                    Timestamp ts = rs.getTimestamp("fechaCompra");
                    c.setFechaCompra(ts != null ? ts.toLocalDateTime() : null);
                    c.setMontoTotal(rs.getBigDecimal("montoTotal") != null ? rs.getBigDecimal("montoTotal").doubleValue() : null);
                    c.setMetodoPago(rs.getString("metodoPago"));
                    c.setPuntosGenerados(rs.getBigDecimal("puntosGenerados") != null ? rs.getBigDecimal("puntosGenerados").doubleValue() : null);

                    Cliente cl = new Cliente();
                    cl.setIdCliente(rs.getInt("cl_id"));
                    c.setCliente(cl);

                    Cajero ca = new Cajero();
                    ca.setIdCajero(rs.getInt("ca_id"));
                    c.setCajero(ca);

                    return c;
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo compra", e);
        }
    }

    @Override
    public Compra update(Compra comp) {
        String sql = "UPDATE compra SET montoTotal=?, metodoPago=?, puntosGenerados=?, idCliente=?, idCajero=? WHERE idCompra=?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, comp.getMontoTotal());
            ps.setString(2, comp.getMetodoPago());
            ps.setDouble(3, comp.getPuntosGenerados());
            ps.setInt(4, comp.getCliente().getIdCliente());
            ps.setInt(5, comp.getCajero().getIdCajero());
            ps.setInt(6, comp.getIdCompra());
            int affected = ps.executeUpdate();
            return affected > 0 ? read(comp.getIdCompra()) : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando compra", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM compra WHERE idCompra = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando compra", e);
        }
    }

    @Override
    public List<Compra> findAll() {
        String sql = "SELECT co.*, cl.idCliente AS cl_id, ca.idCajero AS ca_id FROM compra co JOIN cliente cl ON co.idCliente = cl.idCliente JOIN cajero ca ON co.idCajero = ca.idCajero ORDER BY co.idCompra";
        List<Compra> list = new ArrayList<>();
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Compra c = new Compra();
                c.setIdCompra(rs.getInt("idCompra"));
                Timestamp ts = rs.getTimestamp("fechaCompra");
                c.setFechaCompra(ts != null ? ts.toLocalDateTime() : null);
                c.setMontoTotal(rs.getBigDecimal("montoTotal") != null ? rs.getBigDecimal("montoTotal").doubleValue() : null);
                c.setMetodoPago(rs.getString("metodoPago"));
                c.setPuntosGenerados(rs.getBigDecimal("puntosGenerados") != null ? rs.getBigDecimal("puntosGenerados").doubleValue() : null);

                Cliente cl = new Cliente();
                cl.setIdCliente(rs.getInt("cl_id"));
                c.setCliente(cl);

                Cajero ca = new Cajero();
                ca.setIdCajero(rs.getInt("ca_id"));
                c.setCajero(ca);

                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error listando compras", e);
        }
    }
}
