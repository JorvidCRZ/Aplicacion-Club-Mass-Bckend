package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.IncidenciaDAO;
import backendClubMass.backendClubMass.model.Incidencia;
import backendClubMass.backendClubMass.model.Cliente;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncidenciaDAOImpl implements IncidenciaDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public Incidencia create(Incidencia i) {
        String sql = "INSERT INTO incidencia (idCliente, descripcion, estado) VALUES (?,?,?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, i.getCliente().getIdCliente());
            ps.setString(2, i.getDescripcion());
            ps.setString(3, i.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) i.setIdIncidencia(rs.getInt(1));
            }
            return read(i.getIdIncidencia());
        } catch (SQLException e) {
            throw new RuntimeException("Error creando incidencia", e);
        }
    }

    @Override
    public Incidencia read(Integer id) {
        String sql = "SELECT inc.*, cl.idCliente, cl.dni FROM incidencia inc JOIN cliente cl ON inc.idCliente = cl.idCliente WHERE inc.idIncidencia = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Incidencia inc = new Incidencia();
                    inc.setIdIncidencia(rs.getInt("idIncidencia"));
                    Cliente cl = new Cliente();
                    cl.setIdCliente(rs.getInt("idCliente"));
                    cl.setDni(rs.getString("dni"));
                    inc.setCliente(cl);
                    inc.setDescripcion(rs.getString("descripcion"));
                    inc.setEstado(rs.getString("estado"));
                    Timestamp ts = rs.getTimestamp("fecha");
                    inc.setFecha(ts != null ? ts.toLocalDateTime() : null);
                    return inc;
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo incidencia", e);
        }
    }

    @Override
    public Incidencia update(Incidencia i) {
        String sql = "UPDATE incidencia SET idCliente=?, descripcion=?, estado=? WHERE idIncidencia=?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, i.getCliente().getIdCliente());
            ps.setString(2, i.getDescripcion());
            ps.setString(3, i.getEstado());
            ps.setInt(4, i.getIdIncidencia());
            int affected = ps.executeUpdate();
            return affected > 0 ? read(i.getIdIncidencia()) : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando incidencia", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM incidencia WHERE idIncidencia = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando incidencia", e);
        }
    }

    @Override
    public List<Incidencia> findAll() {
        String sql = "SELECT inc.*, cl.idCliente, cl.dni FROM incidencia inc JOIN cliente cl ON inc.idCliente = cl.idCliente ORDER BY inc.idIncidencia";
        List<Incidencia> list = new ArrayList<>();
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Incidencia inc = new Incidencia();
                inc.setIdIncidencia(rs.getInt("idIncidencia"));
                Cliente cl = new Cliente();
                cl.setIdCliente(rs.getInt("idCliente"));
                cl.setDni(rs.getString("dni"));
                inc.setCliente(cl);
                inc.setDescripcion(rs.getString("descripcion"));
                inc.setEstado(rs.getString("estado"));
                Timestamp ts = rs.getTimestamp("fecha");
                inc.setFecha(ts != null ? ts.toLocalDateTime() : null);
                list.add(inc);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error listando incidencias", e);
        }
    }

    @Override
    public List<Incidencia> findByCliente(Integer idCliente) {
        String sql = "SELECT inc.*, cl.idCliente, cl.dni FROM incidencia inc JOIN cliente cl ON inc.idCliente = cl.idCliente WHERE inc.idCliente = ? ORDER BY inc.idIncidencia";
        List<Incidencia> list = new ArrayList<>();
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Incidencia inc = new Incidencia();
                    inc.setIdIncidencia(rs.getInt("idIncidencia"));
                    Cliente cl = new Cliente();
                    cl.setIdCliente(rs.getInt("idCliente"));
                    cl.setDni(rs.getString("dni"));
                    inc.setCliente(cl);
                    inc.setDescripcion(rs.getString("descripcion"));
                    inc.setEstado(rs.getString("estado"));
                    Timestamp ts = rs.getTimestamp("fecha");
                    inc.setFecha(ts != null ? ts.toLocalDateTime() : null);
                    list.add(inc);
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando incidencias por cliente", e);
        }
    }
}
