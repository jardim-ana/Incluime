package incluime.conectamais;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexao {

    private final DataSource dataSource;

    public Conexao() {

        public Conexao() {

                DriverManagerDataSource driver =
                        new DriverManagerDataSource();

                driver.setDriverClassName(
                        System.getenv("DB_DRIVER")
                );

                driver.setUrl(
                        System.getenv("DB_URL")
                );

                driver.setUsername(
                        System.getenv("DB_USER")
                );

                driver.setPassword(
                        System.getenv("DB_PASSWORD")
                );

                this.dataSource = driver;
        }
    }
    
    public Connection getConexao()
            throws SQLException {

        return dataSource.getConnection();
    }
}