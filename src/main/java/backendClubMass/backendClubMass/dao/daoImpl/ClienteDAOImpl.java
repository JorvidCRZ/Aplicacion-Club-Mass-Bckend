package backendClubMass.backendClubMass.dao.daoImpl;

import backendClubMass.backendClubMass.dao.ClienteDAO;
import backendClubMass.backendClubMass.model.Cliente;
import backendClubMass.backendClubMass.model.Usuario;
import backendClubMass.backendClubMass.utils.HikariDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    private final HikariDataAccess hikari;

    public ClienteDAOImpl() {
        this.hikari = new HikariDataAccess();
    }

    @Override
    public Cliente create(Cliente cliente) {
        String sql = "INSERT INTO cliente (telefono, dni, codigoMembresia, idUsuario) VALUES (?, ?, ?, ?)";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getTelefono());
            ps.setString(2, cliente.getDni());
            ps.setString(3, cliente.getCodigoMembresia());

            if (cliente.getUsuario() != null && cliente.getUsuario().getIdUsuario() != null) {
                ps.setInt(4, cliente.getUsuario().getIdUsuario());
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    cliente.setIdCliente(keys.getInt(1));
                }
            }

            return read(cliente.getIdCliente());

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear Cliente", e);
        }
    }

    @Override
    public Cliente read(Integer id) {
        String sql = """
                SELECT cl.*, 
                       u.idUsuario AS u_id, u.nombre1 AS u_nombre1, u.nombre2 AS u_nombre2, 
                       u.apellidoPaterno AS u_apellidoPaterno, u.apellidoMaterno AS u_apellidoMaterno,
                       u.username AS u_username, u.correo AS u_correo, u.rol_id AS u_rol_id
                FROM cliente cl
                LEFT JOIN usuario u ON cl.idUsuario = u.idUsuario
                WHERE cl.idCliente = ?
                """;

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al leer Cliente", e);
        }
    }

    @Override
    public Cliente update(Cliente cliente) {
        String sql = "UPDATE cliente SET telefono = ?, dni = ?, codigoMembresia = ?, idUsuario = ? WHERE idCliente = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getTelefono());
            ps.setString(2, cliente.getDni());
            ps.setString(3, cliente.getCodigoMembresia());

            if (cliente.getUsuario() != null && cliente.getUsuario().getIdUsuario() != null) {
                ps.setInt(4, cliente.getUsuario().getIdUsuario());
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.setInt(5, cliente.getIdCliente());

            int updated = ps.executeUpdate();

            return updated > 0 ? read(cliente.getIdCliente()) : null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar Cliente", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar Cliente", e);
        }
    }

    @Override
    public List<Cliente> findAll() {
        String sql = """
                SELECT cl.*, 
                       u.idUsuario AS u_id, u.nombre1 AS u_nombre1, u.nombre2 AS u_nombre2, 
                       u.apellidoPaterno AS u_apellidoPaterno, u.apellidoMaterno AS u_apellidoMaterno,
                       u.username AS u_username, u.correo AS u_correo, u.rol_id AS u_rol_id
                FROM cliente cl
                LEFT JOIN usuario u ON cl.idUsuario = u.idUsuario
                ORDER BY cl.idCliente
                """;

        List<Cliente> lista = new ArrayList<>();

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) lista.add(mapRow(rs));
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar Clientes", e);
        }
    }

    @Override
    public Cliente findByDni(String dni) {
        String sql = """
                SELECT cl.*, 
                       u.idUsuario AS u_id, u.nombre1 AS u_nombre1, u.nombre2 AS u_nombre2, 
                       u.apellidoPaterno AS u_apellidoPaterno, u.apellidoMaterno AS u_apellidoMaterno,
                       u.username AS u_username, u.correo AS u_correo, u.rol_id AS u_rol_id
                FROM cliente cl
                LEFT JOIN usuario u ON cl.idUsuario = u.idUsuario
                WHERE cl.dni = ?
                """;

        try (Connection conn = hikari.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar Cliente por DNI", e);
        }
    }

    private Cliente mapRow(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("idCliente"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setDni(rs.getString("dni"));
        cliente.setCodigoMembresia(rs.getString("codigoMembresia"));

        Timestamp ts = rs.getTimestamp("fechaRegistro");
        cliente.setFechaRegistro(ts != null ? ts.toLocalDateTime() : null);

        int idUsuario = rs.getInt("u_id");
        if (!rs.wasNull()) {
            Usuario u = new Usuario();
            u.setIdUsuario(idUsuario);
            u.setNombre1(rs.getString("u_nombre1"));
            u.setNombre2(rs.getString("u_nombre2"));
            u.setApellidoPaterno(rs.getString("u_apellidoPaterno"));
            u.setApellidoMaterno(rs.getString("u_apellidoMaterno"));
            u.setUsername(rs.getString("u_username"));
            u.setCorreo(rs.getString("u_correo"));
            cliente.setUsuario(u);
        }

        return cliente;
    }
}
