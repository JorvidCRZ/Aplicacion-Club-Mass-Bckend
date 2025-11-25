package backendClubMass.backendClubMass.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariDataAccess {

private static HikariDataSource dataSource;

static {
    try {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/club_mass?useSSL=false&serverTimezone=UTC");
        config.setUsername("root");
        config.setPassword("JorvixVonmCRZ2023*");

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(3);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);
        config.setMaxLifetime(1800000);

        dataSource = new HikariDataSource(config);

    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Error inicializando HikariCP");
    }
}

public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
}
}