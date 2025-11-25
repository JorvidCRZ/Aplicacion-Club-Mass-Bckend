package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.DetalleCanjeDAO;
import backendClubMass.backendClubMass.model.DetalleCanje;
import backendClubMass.backendClubMass.model.Premio;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleCanjeDAOImpl implements DetalleCanjeDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public DetalleCanje create(DetalleCanje d) {
        String sql = "INSERT INTO detalle_canje (idCanje, idPremio, cantidad, puntosPorUnidad, subtotalPuntos) VALUES (?,?,?,?,?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, d.getCanje().getIdCanje());
            ps.setInt(2, d.getPremio().getIdPremio());
            ps.setInt(3, d.getCantidad());
            ps.setInt(4, d.getPuntosPorUnidad());
            ps.setInt(5, d.getSubtotalPuntos());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) d.setIdDetalleCanje(rs.getInt(1));
            }
            return read(d.getIdDetalleCanje());
        } catch (SQLException e) {
            throw new RuntimeException("Error creando detalle_canje", e);
        }
    }

    @Override
    public DetalleCanje read(Integer id) {
        String sql = "SELECT dc.*, pr.* FROM detalle_canje dc JOIN premios pr ON dc.idPremio = pr.idPremio WHERE dc.idDetalleCanje = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DetalleCanje dc = new DetalleCanje();
                    dc.setIdDetalleCanje(rs.getInt("idDetalleCanje"));
                    dc.setCantidad(rs.getInt("cantidad"));
                    dc.setPuntosPorUnidad(rs.getInt("puntosPorUnidad"));
                    dc.setSubtotalPuntos(rs.getInt("subtotalPuntos"));
                    Premio pr = new Premio();
                    pr.setIdPremio(rs.getInt("idPremio"));
                    pr.setNombre(rs.getString("nombre"));
                    dc.setPremio(pr);
                    return dc;
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo detalle_canje", e);
        }
    }

    @Override
    public DetalleCanje update(DetalleCanje d) {
        String sql = "UPDATE detalle_canje SET idCanje=?, idPremio=?, cantidad=?, puntosPorUnidad=?, subtotalPuntos=? WHERE idDetalleCanje=?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getCanje().getIdCanje());
            ps.setInt(2, d.getPremio().getIdPremio());
            ps.setInt(3, d.getCantidad());
            ps.setInt(4, d.getPuntosPorUnidad());
            ps.setInt(5, d.getSubtotalPuntos());
            ps.setInt(6, d.getIdDetalleCanje());
            int affected = ps.executeUpdate();
            return affected > 0 ? read(d.getIdDetalleCanje()) : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando detalle_canje", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM detalle_canje WHERE idDetalleCanje = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando detalle_canje", e);
        }
    }

    @Override
    public List<DetalleCanje> findAll() {
        String sql = "SELECT dc.*, pr.* FROM detalle_canje dc JOIN premios pr ON dc.idPremio = pr.idPremio ORDER BY dc.idDetalleCanje";
        List<DetalleCanje> list = new ArrayList<>();
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DetalleCanje dc = new DetalleCanje();
                dc.setIdDetalleCanje(rs.getInt("idDetalleCanje"));
                dc.setCantidad(rs.getInt("cantidad"));
                dc.setPuntosPorUnidad(rs.getInt("puntosPorUnidad"));
                dc.setSubtotalPuntos(rs.getInt("subtotalPuntos"));
                Premio pr = new Premio();
                pr.setIdPremio(rs.getInt("idPremio"));
                pr.setNombre(rs.getString("nombre"));
                dc.setPremio(pr);
                list.add(dc);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error listando detalle_canje", e);
        }
    }
}
