from flask import Flask, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

@app.route("/resposta/<opcao>")
def resposta(opcao):
    respostas = {
        "cadastro": "Para se cadastrar, clique em Login e depois em Criar conta.",
        "escolas": "Selecione primeiro o município e depois escolha a escola.",
        "avaliacoes": "As avaliações aparecem no dashboard.",
        "feedback": "Você pode deixar feedback na área de comentários.",
        "instituicao": "Se você é uma escola ou instituição, escolha o cadastro institucional."
    }

    return jsonify({"mensagem": respostas.get(opcao, "Opção não encontrada.")})

if __name__ == "__main__":
    app.run(debug=True)