package incluime.conectamais;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String nomeArquivo = "escolas-query.xlsx";

        LeitorExcel leitorExcel = new LeitorExcel();
        List<Escola> escolasExtraidas = leitorExcel.extrairEscolas(nomeArquivo);

        Conexao conexao = new Conexao();
        JdbcTemplate template = new JdbcTemplate(conexao.getConexao());

        template.execute("DROP TABLE escola_acessibilidade IF EXISTS;");

        template.execute("CREATE TABLE escola_acessibilidade (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "\n" +
                "ano INT,\n" +
                "sigla_uf VARCHAR(2),\n" +
                "id_municipio INT,\n" +
                "id_municipio_nome VARCHAR(255),\n" +
                "id_escola VARCHAR(50),\n" +
                "rede VARCHAR(50),\n" +
                "tipo_categoria VARCHAR(100),\n" +
                "tipo_localizacao VARCHAR(100),\n" +
                "\n" +
                "banheiro_pne INT,\n" +
                "dependencia_pne INT,\n" +
                "corrimao INT,\n" +
                "elevador INT,\n" +
                "pisos_tateis INT,\n" +
                "vao_livre INT,\n" +
                "rampas INT,\n" +
                "sinais_sonoros INT,\n" +
                "sinal_tatil INT,\n" +
                "sinal_visual INT,\n" +
                "acessibilidade_inexistente INT,\n" +
                "\n" +
                "qtd_sala_util_acessivel INT,\n" +
                "material_pedago_surdo INT,\n" +
                "qtd_matricula_educ_basica INT,\n" +
                "qtd_matricula_especial INT,\n" +
                "qtd_docente_educ_basica INT,\n" +
                "qtd_turma_especial INT,\n" +
                "qtd_turma_especial_comum INT,\n" +
                "qtd_turma_especial_exclusiva INT\n" +
                ");");

        for (Escola escola : escolasExtraidas) {
            template.update(
                    "INSERT INTO escola_acessibilidade (ano, sigla_uf, id_municipio, id_municipio_nome, id_escola, rede, tipo_categoria, tipo_localizacao, " +
                            "banheiro_pne, dependencia_pne, corrimao, elevador, pisos_tateis, vao_livre, rampas, sinais_sonoros, sinal_tatil, sinal_visual, acessibilidade_inexistente, " +
                            "qtd_sala_util_acessivel, material_pedago_surdo, qtd_matricula_educ_basica, qtd_matricula_especial, qtd_docente_educ_basica, qtd_turma_especial, qtd_turma_especial_comum, qtd_turma_especial_exclusiva) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",

                    escola.getAno(),
                    escola.getSiglaUf(),
                    escola.getIdMunicipio(),
                    escola.getIdMunicipioNome(),
                    escola.getIdEscola(),
                    escola.getRede(),
                    escola.getTipoCategoria(),
                    escola.getTipoLocalizacao(),

                    escola.getBanheiroPne(),
                    escola.getDependenciaPne(),
                    escola.getCorrimao(),
                    escola.getElevador(),
                    escola.getPisosTateis(),
                    escola.getVaoLivre(),
                    escola.getRampas(),
                    escola.getSinaisSonoros(),
                    escola.getSinalTatil(),
                    escola.getSinalVisual(),
                    escola.getAcessibilidadeInexistente(),

                    escola.getQtdSalaUtilAcessivel(),
                    escola.getMaterialPedagoSurdo(),
                    escola.getQtdMatriculaEducBasica(),
                    escola.getQtdMatriculaEspecial(),
                    escola.getQtdDocenteEducBasica(),
                    escola.getQtdTurmaEspecial(),
                    escola.getQtdTurmaEspecialComum(),
                    escola.getQtdTurmaEspecialExclusiva()
            );

            break;
        }

        System.out.println("Escolas extraídas:");
        System.out.println(template.query("SELECT * FROM escola_acessibilidade WHERE id = 1", new BeanPropertyRowMapper<>(Escola.class)));
    }
}
