package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.CampañaDAO;
import backendClubMass.backendClubMass.model.Campaña;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CampañaDAOImpl implements CampañaDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    private Campaña mapRow(ResultSet rs) throws SQLException {
        Campaña c = new Campaña();
        c.setIdCampaña(rs.getInt("idCampaña"));
        c.setNombreCampaña(rs.getString("nombreCampaña"));

        Date fi = rs.getDate("fechaInicio");
        Date ff = rs.getDate("fechaFin");

        c.setFechaInicio(fi != null ? fi.toLocalDate() : null);
        c.setFechaFin(ff != null ? ff.toLocalDate() : null);

        c.setDescripcion(rs.getString("descripcion"));
        c.setMultiplicadorPuntos(rs.getDouble("multiplicadorPuntos"));

        c.setEstado(rs.getInt("estado"));
        return c;
    }

    @Override
    public Campaña create(Campaña c) {
        String sql = """
            INSERT INTO campaña 
            (nombreCampaña, fechaInicio, fechaFin, descripcion, multiplicadorPuntos, estado)
            VALUES (?,?,?,?,?,?)
            """;

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNombreCampaña());
            ps.setDate(2, c.getFechaInicio() == null ? null : Date.valueOf(c.getFechaInicio()));
            ps.setDate(3, c.getFechaFin() == null ? null : Date.valueOf(c.getFechaFin()));
            ps.setString(4, c.getDescripcion());
            ps.setDouble(5, c.getMultiplicadorPuntos());
            ps.setInt(6, c.getEstado() == null ? 1 : c.getEstado());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setIdCampaña(rs.getInt(1));
            }

            return read(c.getIdCampaña());

        } catch (SQLException e) {
            throw new RuntimeException("Error creando campaña", e);
        }
    }

    @Override
    public Campaña read(Integer id) {
        String sql = """
            SELECT idCampaña, nombreCampaña, fechaInicio, fechaFin, 
                   descripcion, multiplicadorPuntos, estado
            FROM campaña
            WHERE idCampaña = ?
            """;

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo campaña", e);
        }
    }

    @Override
    public Campaña update(Campaña c) {
        String sql = """
            UPDATE campaña SET
                nombreCampaña=?, fechaInicio=?, fechaFin=?, 
                descripcion=?, multiplicadorPuntos=?, estado=?
            WHERE idCampaña=?
            """;

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombreCampaña());
            ps.setDate(2, c.getFechaInicio() == null ? null : Date.valueOf(c.getFechaInicio()));
            ps.setDate(3, c.getFechaFin() == null ? null : Date.valueOf(c.getFechaFin()));
            ps.setString(4, c.getDescripcion());
            ps.setDouble(5, c.getMultiplicadorPuntos());
            ps.setInt(6, c.getEstado() == null ? 1 : c.getEstado());
            ps.setInt(7, c.getIdCampaña());

            int affected = ps.executeUpdate();
            return affected > 0 ? read(c.getIdCampaña()) : null;

        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando campaña", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM campaña WHERE idCampaña = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando campaña", e);
        }
    }

    @Override
    public List<Campaña> findAll() {
        String sql = """
            SELECT idCampaña, nombreCampaña, fechaInicio, fechaFin, 
                   descripcion, multiplicadorPuntos, estado
            FROM campaña
            ORDER BY idCampaña
            """;

        List<Campaña> list = new ArrayList<>();

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Error listando campañas", e);
        }
    }
}
