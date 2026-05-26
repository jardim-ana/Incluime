package incluime.conectamais;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexao {

    private final DataSource dataSource;

    public Conexao() {

        DriverManagerDataSource driver =
                new DriverManagerDataSource();

        driver.setDriverClassName(
                "com.mysql.cj.jdbc.Driver"
        );

        driver.setUrl(
                "jdbc:mysql://bd:3306/incluime"
        );

        driver.setUsername("root");

        driver.setPassword("incluime100");

        this.dataSource = driver;
    }

    public Connection getConexao()
            throws SQLException {

        return dataSource.getConnection();
    }
}