package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.PuntosDAO;
import backendClubMass.backendClubMass.model.Campaña;
import backendClubMass.backendClubMass.model.Cliente;
import backendClubMass.backendClubMass.model.Puntos;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PuntosDAOImpl implements PuntosDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public Puntos create(Puntos p) {
        String sql = "INSERT INTO puntos (idCliente, idCampaña, puntosGanados, puntosCanjeados) VALUES (?,?,?,?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getCliente().getIdCliente());
            ps.setInt(2, p.getCampaña().getIdCampaña());
            ps.setBigDecimal(3, p.getPuntosGanados() == null ? null : java.math.BigDecimal.valueOf(p.getPuntosGanados()));
            ps.setBigDecimal(4, p.getPuntosCanjeados() == null ? null : java.math.BigDecimal.valueOf(p.getPuntosCanjeados()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setIdPuntos(rs.getInt(1));
            }
            return read(p.getIdPuntos());
        } catch (SQLException e) {
            throw new RuntimeException("Error creando puntos", e);
        }
    }

    @Override
    public Puntos read(Integer id) {
        String sql = "SELECT pt.*, c.nombreCampaña FROM puntos pt JOIN campaña c ON pt.idCampaña = c.idCampaña WHERE pt.idPuntos = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Puntos p = new Puntos();
                    p.setIdPuntos(rs.getInt("idPuntos"));
                    Cliente cl = new Cliente();
                    cl.setIdCliente(rs.getInt("idCliente"));
                    p.setCliente(cl);
                    Campaña camp = new Campaña();
                    camp.setIdCampaña(rs.getInt("idCampaña"));
                    camp.setNombreCampaña(rs.getString("nombreCampaña"));
                    p.setCampaña(camp);
                    p.setPuntosGanados(rs.getBigDecimal("puntosGanados") != null ? rs.getBigDecimal("puntosGanados").doubleValue() : null);
                    p.setPuntosCanjeados(rs.getBigDecimal("puntosCanjeados") != null ? rs.getBigDecimal("puntosCanjeados").doubleValue() : null);
                    Timestamp ts = rs.getTimestamp("fechaRegistro");
                    p.setFechaRegistro(ts != null ? ts.toLocalDateTime() : null);
                    return p;
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo puntos", e);
        }
    }

    @Override
    public Puntos update(Puntos p) {
        String sql = "UPDATE puntos SET idCliente=?, idCampaña=?, puntosGanados=?, puntosCanjeados=? WHERE idPuntos=?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getCliente().getIdCliente());
            ps.setInt(2, p.getCampaña().getIdCampaña());
            ps.setBigDecimal(3, p.getPuntosGanados() == null ? null : java.math.BigDecimal.valueOf(p.getPuntosGanados()));
            ps.setBigDecimal(4, p.getPuntosCanjeados() == null ? null : java.math.BigDecimal.valueOf(p.getPuntosCanjeados()));
            ps.setInt(5, p.getIdPuntos());
            int affected = ps.executeUpdate();
            return affected > 0 ? read(p.getIdPuntos()) : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando puntos", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM puntos WHERE idPuntos = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando puntos", e);
        }
    }

    @Override
    public List<Puntos> findAll() {
        String sql = "SELECT pt.*, c.nombreCampaña FROM puntos pt JOIN campaña c ON pt.idCampaña = c.idCampaña ORDER BY pt.idPuntos";
        List<Puntos> list = new ArrayList<>();
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Puntos p = new Puntos();
                p.setIdPuntos(rs.getInt("idPuntos"));
                Cliente cl = new Cliente();
                cl.setIdCliente(rs.getInt("idCliente"));
                p.setCliente(cl);
                Campaña camp = new Campaña();
                camp.setIdCampaña(rs.getInt("idCampaña"));
                camp.setNombreCampaña(rs.getString("nombreCampaña"));
                p.setCampaña(camp);
                p.setPuntosGanados(rs.getBigDecimal("puntosGanados") != null ? rs.getBigDecimal("puntosGanados").doubleValue() : null);
                p.setPuntosCanjeados(rs.getBigDecimal("puntosCanjeados") != null ? rs.getBigDecimal("puntosCanjeados").doubleValue() : null);
                Timestamp ts = rs.getTimestamp("fechaRegistro");
                p.setFechaRegistro(ts != null ? ts.toLocalDateTime() : null);
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error listando puntos", e);
        }
    }

    @Override
    public List<Puntos> findByCliente(Integer idCliente) {
        String sql = "SELECT pt.*, c.nombreCampaña FROM puntos pt JOIN campaña c ON pt.idCampaña = c.idCampaña WHERE pt.idCliente = ? ORDER BY pt.idPuntos";
        List<Puntos> list = new ArrayList<>();
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Puntos p = new Puntos();
                    p.setIdPuntos(rs.getInt("idPuntos"));
                    Cliente cl = new Cliente();
                    cl.setIdCliente(rs.getInt("idCliente"));
                    p.setCliente(cl);
                    Campaña camp = new Campaña();
                    camp.setIdCampaña(rs.getInt("idCampaña"));
                    camp.setNombreCampaña(rs.getString("nombreCampaña"));
                    p.setCampaña(camp);
                    p.setPuntosGanados(rs.getBigDecimal("puntosGanados") != null ? rs.getBigDecimal("puntosGanados").doubleValue() : null);
                    p.setPuntosCanjeados(rs.getBigDecimal("puntosCanjeados") != null ? rs.getBigDecimal("puntosCanjeados").doubleValue() : null);
                    Timestamp ts = rs.getTimestamp("fechaRegistro");
                    p.setFechaRegistro(ts != null ? ts.toLocalDateTime() : null);
                    list.add(p);
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando puntos por cliente", e);
        }
    }

    @Override
    public Double getPuntosDisponibles(Integer idCliente) {
        String sql = "SELECT SUM(puntosGanados - puntosCanjeados) AS puntosDisponibles FROM puntos WHERE idCliente = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    java.math.BigDecimal val = rs.getBigDecimal("puntosDisponibles");
                    return val != null ? val.doubleValue() : 0.0;
                } else return 0.0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error calculando puntos disponibles", e);
        }
    }
}
