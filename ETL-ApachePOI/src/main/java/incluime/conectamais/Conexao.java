package incluime.conectamais;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class Conexao {

    private DataSource conexao;

    public Conexao() {
        DriverManagerDataSource driver = new DriverManagerDataSource();

        driver.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driver.setUrl("jdbc:mysql://bd/incluime?useTimezone=true&serverTimezone=UTC");
        driver.setUsername("root");
        driver.setPassword("incluime100");

        this.conexao = driver;
    }

    public DataSource getConexao() {
        return conexao;
    }
}
