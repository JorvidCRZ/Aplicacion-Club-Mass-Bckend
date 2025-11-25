package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.CajeroDAO;
import backendClubMass.backendClubMass.model.Cajero;
import backendClubMass.backendClubMass.model.Usuario;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CajeroDAOImpl implements CajeroDAO {

    private final HikariDataAccess hikari = new HikariDataAccess();

    @Override
    public Cajero create(Cajero c) {
        String sql = "INSERT INTO cajero (idUsuario, estado) VALUES (?, ?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, c.getUsuario().getIdUsuario());
            ps.setString(2, c.getEstado());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setIdCajero(rs.getInt(1));
            }

            return read(c.getIdCajero());
        } catch (SQLException e) {
            throw new RuntimeException("Error creando cajero", e);
        }
    }

    @Override
    public Cajero read(Integer id) {
        String sql = """
            SELECT 
                ca.idCajero,
                ca.estado AS cajero_estado,
                
                u.idUsuario AS u_idUsuario,
                u.nombre1 AS u_nombre1,
                u.apellidoPaterno AS u_apellidoPaterno,
                u.username AS u_username,
                u.correo AS u_correo

            FROM cajero ca
            JOIN usuario u ON ca.idUsuario = u.idUsuario
            WHERE ca.idCajero = ?
        """;

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) return null;

                Cajero c = new Cajero();
                c.setIdCajero(rs.getInt("idCajero"));
                c.setEstado(rs.getString("cajero_estado"));

                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("u_idUsuario"));
                u.setNombre1(rs.getString("u_nombre1"));
                u.setApellidoPaterno(rs.getString("u_apellidoPaterno"));
                u.setUsername(rs.getString("u_username"));
                u.setCorreo(rs.getString("u_correo"));

                c.setUsuario(u);

                return c;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo cajero", e);
        }
    }

    @Override
    public Cajero update(Cajero c) {
        String sql = "UPDATE cajero SET idUsuario = ?, estado = ? WHERE idCajero = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getUsuario().getIdUsuario());
            ps.setString(2, c.getEstado());
            ps.setInt(3, c.getIdCajero());

            int affected = ps.executeUpdate();
            return affected > 0 ? read(c.getIdCajero()) : null;

        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando cajero", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM cajero WHERE idCajero = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando cajero", e);
        }
    }

    @Override
    public List<Cajero> findAll() {
        String sql = """
            SELECT 
                ca.idCajero,
                ca.estado AS cajero_estado,
                
                u.idUsuario AS u_idUsuario,
                u.nombre1 AS u_nombre1,
                u.apellidoPaterno AS u_apellidoPaterno,
                u.username AS u_username,
                u.correo AS u_correo

            FROM cajero ca
            JOIN usuario u ON ca.idUsuario = u.idUsuario
            ORDER BY ca.idCajero
        """;

        List<Cajero> list = new ArrayList<>();

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cajero c = new Cajero();
                c.setIdCajero(rs.getInt("idCajero"));
                c.setEstado(rs.getString("cajero_estado"));

                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("u_idUsuario"));
                u.setNombre1(rs.getString("u_nombre1"));
                u.setApellidoPaterno(rs.getString("u_apellidoPaterno"));
                u.setUsername(rs.getString("u_username"));
                u.setCorreo(rs.getString("u_correo"));

                c.setUsuario(u);
                list.add(c);
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Error listando cajeros", e);
        }
    }
}

