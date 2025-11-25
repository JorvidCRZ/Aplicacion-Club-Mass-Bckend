package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.CanjeDAO;
import backendClubMass.backendClubMass.model.Canje;
import backendClubMass.backendClubMass.model.Cliente;
import backendClubMass.backendClubMass.model.Cajero;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CanjeDAOImpl implements CanjeDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public Canje create(Canje c) {
        String sql = "INSERT INTO canje (idCliente, idCajero, puntosUsados) VALUES (?,?,?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, c.getCliente().getIdCliente());
            ps.setInt(2, c.getCajero().getIdCajero());
            ps.setDouble(3, c.getPuntosUsados()); // ← double

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setIdCanje(rs.getInt(1));
            }

            return read(c.getIdCanje());
        } catch (SQLException e) {
            throw new RuntimeException("Error creando canje", e);
        }
    }

    @Override
    public Canje read(Integer id) {
        String sql = "SELECT * FROM canje WHERE idCanje = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Canje c = new Canje();
                    c.setIdCanje(rs.getInt("idCanje"));

                    Timestamp ts = rs.getTimestamp("fechaCanje");
                    c.setFechaCanje(ts != null ? ts.toLocalDateTime() : null);

                    Cliente cl = new Cliente();
                    cl.setIdCliente(rs.getInt("idCliente"));
                    c.setCliente(cl);

                    Cajero ca = new Cajero();
                    ca.setIdCajero(rs.getInt("idCajero"));
                    c.setCajero(ca);

                    // ← ahora double
                    c.setPuntosUsados(rs.getDouble("puntosUsados"));

                    return c;
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo canje", e);
        }
    }

    @Override
    public Canje update(Canje c) {
        String sql = "UPDATE canje SET idCliente=?, idCajero=?, puntosUsados=? WHERE idCanje=?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getCliente().getIdCliente());
            ps.setInt(2, c.getCajero().getIdCajero());
            ps.setDouble(3, c.getPuntosUsados()); // ← double
            ps.setInt(4, c.getIdCanje());

            int affected = ps.executeUpdate();
            return affected > 0 ? read(c.getIdCanje()) : null;

        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando canje", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM canje WHERE idCanje = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando canje", e);
        }
    }

    @Override
    public List<Canje> findAll() {
        String sql = "SELECT * FROM canje ORDER BY idCanje";
        List<Canje> list = new ArrayList<>();

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Canje c = new Canje();
                c.setIdCanje(rs.getInt("idCanje"));

                Timestamp ts = rs.getTimestamp("fechaCanje");
                c.setFechaCanje(ts != null ? ts.toLocalDateTime() : null);

                Cliente cl = new Cliente();
                cl.setIdCliente(rs.getInt("idCliente"));
                c.setCliente(cl);

                Cajero ca = new Cajero();
                ca.setIdCajero(rs.getInt("idCajero"));
                c.setCajero(ca);

                c.setPuntosUsados(rs.getDouble("puntosUsados")); // ← double

                list.add(c);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Error listando canjes", e);
        }
    }
}
