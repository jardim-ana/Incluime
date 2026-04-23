package incluime.conectamais;

import incluime.conectamais.client.S3Provider;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Inicio da MAIN");

        S3Client s3 = new S3Provider().getClient();

        LeitorExcel leitor = new LeitorExcel(s3);

        List<Escola> escolasExtraidas = leitor.extrairEscolas(
                "incluimebucket",
                "excel/escolas-query.xlsx"
        );

        Conexao conexao = new Conexao();
        JdbcTemplate template = new JdbcTemplate(conexao.getConexao());

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
