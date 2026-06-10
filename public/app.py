from flask import Flask, jsonify, request
from flask_cors import CORS
import unicodedata

app = Flask(__name__)
CORS(app)


def normalizar_texto(texto):
    texto = texto.lower().strip()

    texto = unicodedata.normalize("NFD", texto)
    texto = texto.encode("ascii", "ignore")
    texto = texto.decode("utf-8")

    return texto


def gerar_resposta(pergunta):
    pergunta = normalizar_texto(pergunta)

    if pergunta == "":
        return "Digite uma pergunta para que eu possa te ajudar."


    if any(palavra in pergunta for palavra in [
        "o que e inclui.me",
        "o que e a inclui.me",
        "inclui.me",
        "incluime",
        "inclui me",
        "sobre a inclui",
        "sobre o inclui"
    ]):
        return (
            "A inclui.me é uma plataforma voltada à inclusão e acessibilidade, "
            "criada para apoiar famílias, estudantes e instituições na busca por informações "
            "sobre escolas e condições de acessibilidade."
        )

  
    elif any(palavra in pergunta for palavra in [
        "conecta",
        "conecta+",
        "o que e conecta",
        "o que e o conecta",
        "para que serve o conecta",
        "conecta mais",
        "conectamais"
    ]):
        return (
            "O Conecta+ é uma solução da inclui.me criada para ajudar famílias e estudantes "
            "a encontrarem escolas com melhores condições de acessibilidade. "
            "A proposta é reunir informações, avaliações e dados para facilitar essa escolha."
        )

    elif any(palavra in pergunta for palavra in [
        "objetivo",
        "finalidade",
        "proposito",
        "para que serve",
        "qual a ideia",
        "qual e a ideia"
    ]):
        return (
            "O objetivo da inclui.me é facilitar o acesso a informações sobre acessibilidade escolar, "
            "ajudando famílias a tomarem decisões mais conscientes e incentivando melhorias nas instituições."
        )


    elif any(palavra in pergunta for palavra in [
        "quem pode usar",
        "quem usa",
        "usuarios",
        "usuario",
        "familia",
        "estudante",
        "aluno",
        "responsavel",
        "pais",
        "maes",
        "pessoas"
    ]):
        return (
            "A plataforma pode ser usada por estudantes, familiares, responsáveis, escolas, "
            "instituições e pessoas interessadas em informações sobre acessibilidade escolar."
        )


    elif any(palavra in pergunta for palavra in [
        "cadastro",
        "cadastrar",
        "criar conta",
        "fazer conta",
        "conta",
        "login",
        "entrar",
        "acessar"
    ]):
        return (
            "Para se cadastrar, clique em Login e depois em Criar conta. "
            "Depois, preencha seus dados para acessar os recursos da plataforma."
        )

   
    elif any(palavra in pergunta for palavra in [
        "sou escola",
        "sou instituicao",
        "represento uma escola",
        "represento uma instituicao",
        "cadastro institucional",
        "instituicao",
        "institucional",
        "minha escola",
        "diretor",
        "coordenador"
    ]):
        return (
            "Se você representa uma escola ou instituição, escolha o cadastro institucional. "
            "Assim, será possível acompanhar informações relacionadas à sua instituição."
        )

 
    elif any(palavra in pergunta for palavra in [
        "escola",
        "escolas",
        "municipio",
        "cidade",
        "encontrar escola",
        "buscar escola",
        "procurar escola",
        "achar escola",
        "selecionar escola"
    ]):
        return (
            "Para encontrar escolas, selecione primeiro o município desejado. "
            "Depois, escolha uma escola na lista para visualizar informações, avaliações e dados de acessibilidade."
        )


    elif any(palavra in pergunta for palavra in [
        "avaliacao",
        "avaliacoes",
        "avaliar",
        "nota",
        "classificacao",
        "estrelas"
    ]):
        return (
            "As avaliações ajudam outras famílias e estudantes a entenderem melhor a realidade da escola. "
            "Você pode avaliar aspectos relacionados à acessibilidade, estrutura e experiência na instituição."
        )


    elif any(palavra in pergunta for palavra in [
        "feedback",
        "comentario",
        "comentarios",
        "opiniao",
        "sugestao",
        "reclamacao",
        "contato",
        "contate-nos",
        "falar com voces",
        "mensagem"
    ]):
        return (
            "Você pode deixar seu feedback na área de Contate-nos. "
            "Sua opinião ajuda a melhorar a plataforma e contribui para uma experiência mais inclusiva."
        )


    elif any(palavra in pergunta for palavra in [
        "dashboard",
        "painel",
        "dados",
        "indicadores",
        "graficos",
        "relatorio",
        "informacoes"
    ]):
        return (
            "O dashboard apresenta informações e indicadores sobre escolas, acessibilidade, avaliações "
            "e dados relevantes para apoiar a análise das condições de inclusão."
        )


    elif any(palavra in pergunta for palavra in [
        "dados publicos",
        "censo escolar",
        "inep",
        "base dos dados",
        "confiavel",
        "confiabilidade",
        "de onde vem os dados",
        "origem dos dados"
    ]):
        return (
            "A plataforma pode utilizar dados públicos, como informações educacionais, "
            "junto com avaliações e feedbacks dos usuários para apresentar uma visão mais completa."
        )


    elif any(palavra in pergunta for palavra in [
        "gratuito",
        "gratis",
        "pago",
        "paga",
        "custa",
        "valor",
        "preco",
        "tem que pagar"
    ]):
        return (
            "A proposta da inclui.me é oferecer acesso gratuito às informações principais, "
            "facilitando a busca por escolas mais acessíveis."
        )


    elif any(palavra in pergunta for palavra in [
        "nao encontrei",
        "nao achei",
        "nao aparece",
        "nao esta aparecendo",
        "escola nao aparece",
        "escola nao encontrada"
    ]):
        return (
            "Se você não encontrou uma escola, verifique se o município foi selecionado corretamente. "
            "Caso ainda não apareça, a escola pode não estar cadastrada ou disponível na base utilizada."
        )

    elif any(palavra in pergunta for palavra in [
        "acessibilidade",
        "inclusao",
        "deficiencia",
        "pcd",
        "rampa",
        "elevador",
        "libras",
        "banheiro adaptado",
        "mobilidade",
        "educacao inclusiva"
    ]):
        return (
            "A acessibilidade envolve recursos e condições que ajudam pessoas com deficiência ou necessidades específicas, "
            "como rampas, banheiros adaptados, sinalização, apoio pedagógico, Libras e estrutura adequada."
        )

  
    elif any(palavra in pergunta for palavra in [
        "seguranca",
        "privacidade",
        "meus dados",
        "dados pessoais",
        "lgpd"
    ]):
        return (
            "A inclui.me busca tratar as informações com responsabilidade. "
            "Dados pessoais devem ser utilizados apenas para melhorar a experiência do usuário e apoiar o funcionamento da plataforma."
        )

    elif any(palavra in pergunta for palavra in [
        "ajuda",
        "me ajuda",
        "duvida",
        "nao sei usar",
        "como funciona",
        "como usar"
    ]):
        return (
            "Posso te ajudar com dúvidas sobre a inclui.me, o Conecta+, cadastro, busca por escolas, "
            "avaliações, feedback, acessibilidade, dashboard e cadastro institucional."
        )


    elif any(palavra in pergunta for palavra in [
        "oi",
        "ola",
        "bom dia",
        "boa tarde",
        "boa noite",
        "tudo bem"
    ]):
        return (
            "Olá! Sou o assistente da inclui.me. "
            "Você pode me perguntar sobre cadastro, escolas, avaliações, acessibilidade ou sobre o Conecta+."
        )


    else:
        return (
            "Desculpe, não entendi sua pergunta. "
            "Você pode perguntar sobre a inclui.me, o Conecta+, cadastro, escolas, avaliações, feedback, "
            "dashboard, acessibilidade ou instituições."
        )


@app.route("/resposta", methods=["POST"])
def resposta():
    dados = request.get_json()

    pergunta = dados.get("pergunta", "")

    mensagem = gerar_resposta(pergunta)

    return jsonify({"mensagem": mensagem})


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)