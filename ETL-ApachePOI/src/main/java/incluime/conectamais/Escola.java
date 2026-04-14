package incluime.conectamais;

public class Escola {
    private Integer ano;
    private String siglaUf;
    private Integer idMunicipio;
    private String idMunicipioNome;
    private String idEscola;
    private String rede;
    private String tipoCategoria; // Precisa ???
    private String tipoLocalizacao;
    private Integer banheiroPne;
    private Integer dependenciaPne;
    private Integer corrimao;
    private Integer elevador;
    private Integer pisosTateis;
    private Integer vaoLivre;
    private Integer rampas;
    private Integer sinaisSonoros;
    private Integer sinalTatil;
    private Integer sinalVisual;
    private Integer acessibilidadeInexistente;
    private Integer qtdSalaUtilAcessivel;
    private Integer materialPedagoSurdo;
    private Integer qtdMatriculaEducBasica;
    private Integer qtdMatriculaEspecial;
    private Integer qtdDocenteEducBasica;
    private Integer qtdTurmaEspecial;
    private Integer qtdTurmaEspecialComum;
    private Integer qtdTurmaEspecialExclusiva;

    public Escola() {
    }

    public Escola(Integer ano, String siglaUf, Integer idMunicipio, String idMunicipioNome, String idEscola, String rede, String tipoCategoria, String tipoLocalizacao, Integer banheiroPne, Integer dependenciaPne, Integer corrimao, Integer elevador, Integer pisosTateis, Integer vaoLivre, Integer rampas, Integer sinaisSonoros, Integer sinalTatil, Integer sinalVisual, Integer acessibilidadeInexistente, Integer qtdSalaUtilAcessivel, Integer materialPedagoSurdo, Integer qtdMatriculaEducBasica, Integer qtdMatriculaEspecial, Integer qtdDocenteEducBasica, Integer qtdTurmaEspecial, Integer qtdTurmaEspecialComum, Integer qtdTurmaEspecialExclusiva) {
        this.ano = ano;
        this.siglaUf = siglaUf;
        this.idMunicipio = idMunicipio;
        this.idMunicipioNome = idMunicipioNome;
        this.idEscola = idEscola;
        this.rede = rede;
        this.tipoCategoria = tipoCategoria;
        this.tipoLocalizacao = tipoLocalizacao;
        this.banheiroPne = banheiroPne;
        this.dependenciaPne = dependenciaPne;
        this.corrimao = corrimao;
        this.elevador = elevador;
        this.pisosTateis = pisosTateis;
        this.vaoLivre = vaoLivre;
        this.rampas = rampas;
        this.sinaisSonoros = sinaisSonoros;
        this.sinalTatil = sinalTatil;
        this.sinalVisual = sinalVisual;
        this.acessibilidadeInexistente = acessibilidadeInexistente;
        this.qtdSalaUtilAcessivel = qtdSalaUtilAcessivel;
        this.materialPedagoSurdo = materialPedagoSurdo;
        this.qtdMatriculaEducBasica = qtdMatriculaEducBasica;
        this.qtdMatriculaEspecial = qtdMatriculaEspecial;
        this.qtdDocenteEducBasica = qtdDocenteEducBasica;
        this.qtdTurmaEspecial = qtdTurmaEspecial;
        this.qtdTurmaEspecialComum = qtdTurmaEspecialComum;
        this.qtdTurmaEspecialExclusiva = qtdTurmaEspecialExclusiva;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getIdMunicipioNome() {
        return idMunicipioNome;
    }

    public void setIdMunicipioNome(String idMunicipioNome) {
        this.idMunicipioNome = idMunicipioNome;
    }

    public String getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(String idEscola) {
        this.idEscola = idEscola;
    }

    public String getRede() {
        return rede;
    }

    public void setRede(String rede) {
        this.rede = rede;
    }

    public String getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(String tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public String getTipoLocalizacao() {
        return tipoLocalizacao;
    }

    public void setTipoLocalizacao(String tipoLocalizacao) {
        this.tipoLocalizacao = tipoLocalizacao;
    }

    public Integer getBanheiroPne() {
        return banheiroPne;
    }

    public void setBanheiroPne(Integer banheiroPne) {
        this.banheiroPne = banheiroPne;
    }

    public Integer getDependenciaPne() {
        return dependenciaPne;
    }

    public void setDependenciaPne(Integer dependenciaPne) {
        this.dependenciaPne = dependenciaPne;
    }

    public Integer getCorrimao() {
        return corrimao;
    }

    public void setCorrimao(Integer corrimao) {
        this.corrimao = corrimao;
    }

    public Integer getElevador() {
        return elevador;
    }

    public void setElevador(Integer elevador) {
        this.elevador = elevador;
    }

    public Integer getPisosTateis() {
        return pisosTateis;
    }

    public void setPisosTateis(Integer pisosTateis) {
        this.pisosTateis = pisosTateis;
    }

    public Integer getVaoLivre() {
        return vaoLivre;
    }

    public void setVaoLivre(Integer vaoLivre) {
        this.vaoLivre = vaoLivre;
    }

    public Integer getRampas() {
        return rampas;
    }

    public void setRampas(Integer rampas) {
        this.rampas = rampas;
    }

    public Integer getSinaisSonoros() {
        return sinaisSonoros;
    }

    public void setSinaisSonoros(Integer sinaisSonoros) {
        this.sinaisSonoros = sinaisSonoros;
    }

    public Integer getSinalTatil() {
        return sinalTatil;
    }

    public void setSinalTatil(Integer sinalTatil) {
        this.sinalTatil = sinalTatil;
    }

    public Integer getSinalVisual() {
        return sinalVisual;
    }

    public void setSinalVisual(Integer sinalVisual) {
        this.sinalVisual = sinalVisual;
    }

    public Integer getAcessibilidadeInexistente() {
        return acessibilidadeInexistente;
    }

    public void setAcessibilidadeInexistente(Integer acessibilidadeInexistente) {
        this.acessibilidadeInexistente = acessibilidadeInexistente;
    }

    public Integer getQtdSalaUtilAcessivel() {
        return qtdSalaUtilAcessivel;
    }

    public void setQtdSalaUtilAcessivel(Integer qtdSalaUtilAcessivel) {
        this.qtdSalaUtilAcessivel = qtdSalaUtilAcessivel;
    }

    public Integer getMaterialPedagoSurdo() {
        return materialPedagoSurdo;
    }

    public void setMaterialPedagoSurdo(Integer materialPedagoSurdo) {
        this.materialPedagoSurdo = materialPedagoSurdo;
    }

    public Integer getQtdMatriculaEducBasica() {
        return qtdMatriculaEducBasica;
    }

    public void setQtdMatriculaEducBasica(Integer qtdMatriculaEducBasica) {
        this.qtdMatriculaEducBasica = qtdMatriculaEducBasica;
    }

    public Integer getQtdMatriculaEspecial() {
        return qtdMatriculaEspecial;
    }

    public void setQtdMatriculaEspecial(Integer qtdMatriculaEspecial) {
        this.qtdMatriculaEspecial = qtdMatriculaEspecial;
    }

    public Integer getQtdDocenteEducBasica() {
        return qtdDocenteEducBasica;
    }

    public void setQtdDocenteEducBasica(Integer qtdDocenteEducBasica) {
        this.qtdDocenteEducBasica = qtdDocenteEducBasica;
    }

    public Integer getQtdTurmaEspecial() {
        return qtdTurmaEspecial;
    }

    public void setQtdTurmaEspecial(Integer qtdTurmaEspecial) {
        this.qtdTurmaEspecial = qtdTurmaEspecial;
    }

    public Integer getQtdTurmaEspecialComum() {
        return qtdTurmaEspecialComum;
    }

    public void setQtdTurmaEspecialComum(Integer qtdTurmaEspecialComum) {
        this.qtdTurmaEspecialComum = qtdTurmaEspecialComum;
    }

    public Integer getQtdTurmaEspecialExclusiva() {
        return qtdTurmaEspecialExclusiva;
    }

    public void setQtdTurmaEspecialExclusiva(Integer qtdTurmaEspecialExclusiva) {
        this.qtdTurmaEspecialExclusiva = qtdTurmaEspecialExclusiva;
    }

    @Override
    public String toString() {
        return "Escola{" +
                "ano='" + ano + '\'' +
                ", siglaUf='" + siglaUf + '\'' +
                ", idMunicipio='" + idMunicipio + '\'' +
                ", idMunicipioNome='" + idMunicipioNome + '\'' +
                ", idEscola='" + idEscola + '\'' +
                ", rede='" + rede + '\'' +
                ", tipoCategoria='" + tipoCategoria + '\'' +
                ", tipoLocalizacao='" + tipoLocalizacao + '\'' +
                ", banheiroPne=" + banheiroPne +
                ", dependenciaPne=" + dependenciaPne +
                ", corrimao=" + corrimao +
                ", elevador=" + elevador +
                ", pisosTateis=" + pisosTateis +
                ", vaoLivre=" + vaoLivre +
                ", rampas=" + rampas +
                ", sinaisSonoros=" + sinaisSonoros +
                ", sinalTatil=" + sinalTatil +
                ", sinalVisual=" + sinalVisual +
                ", acessibilidadeInexistente=" + acessibilidadeInexistente +
                ", qtdSalaUtilAcessivel=" + qtdSalaUtilAcessivel +
                ", materialPedagoSurdo=" + materialPedagoSurdo +
                ", qtdMatriculaEducBasica=" + qtdMatriculaEducBasica +
                ", qtdMatriculaEspecial=" + qtdMatriculaEspecial +
                ", qtdDocenteEducBasica=" + qtdDocenteEducBasica +
                ", qtdTurmaEspecial=" + qtdTurmaEspecial +
                ", qtdTurmaEspecialComum=" + qtdTurmaEspecialComum +
                ", qtdTurmaEspecialExclusiva=" + qtdTurmaEspecialExclusiva +
                '}';
    }
}
