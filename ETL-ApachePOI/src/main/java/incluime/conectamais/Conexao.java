package incluime.conectamais;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class Conexao {

    private DataSource conexao;

    public Conexao() {
        DriverManagerDataSource driver = new DriverManagerDataSource();

        driver.setDriverClassName("org.h2.Driver");
        driver.setUrl("jdbc:h2:file:./banco-de-dados");
        driver.setUsername("sa");
        driver.setPassword("");

        this.conexao = driver;
    }

    public DataSource getConexao() {
        return conexao;
    }
}
