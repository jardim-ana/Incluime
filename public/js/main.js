var menu = document.querySelector(".menu");
var ul = document.querySelector('.ul');
var links = document.querySelectorAll(".ul a");
var video = document.querySelectorAll(".clique");

document.addEventListener("DOMContentLoaded", function () {

    var botaoVideo = document.getElementById("verVideo");
    var conectaDiv = document.querySelector(".conecta");

    botaoVideo.addEventListener("click", function () {

        conectaDiv.innerHTML = `
            <video id="videoConecta" controls autoplay width="400" style="padding: 20px 20px;">
                <source src="assets/imgs/videoconecta.mp4" type="video/mp4">
                Seu navegador não suporta vídeo.
            </video>
        `;

        var video = document.getElementById("videoConecta");

        video.addEventListener("ended", function () {
            conectaDiv.innerHTML = `
                <img src="assets/imgs/conecta_logo_transparente.png" alt="conecta">
            `;
        });

    });

});



menu.addEventListener('click', ()=>{
  
    if (ul.classList.contains('ativo')) {
        ul.classList.remove('ativo');
        document.querySelector('.menu img').src = 'assets/icon/menu.png'
    }
    else {
        ul.classList.add('ativo');
        document.querySelector('.menu img').src = 'assets/icon/close.png'
    }

});

for (var i = 0; i < links.length; i++) {
    links[i].addEventListener("click", function () {
        ul.classList.remove("ativo");
        document.querySelector(".menu img").src = "assets/icon/menu.png";
    });
}

function toggleChat() {
  const chat = document.getElementById("chatbot");
  chat.style.display = chat.style.display === "block" ? "none" : "block";
}

async function buscarResposta(opcao) {
  const resposta = await fetch(`http://127.0.0.1:5000/resposta/${opcao}`);
  const dados = await resposta.json();
  document.getElementById("resposta").innerText = dados.mensagem;
}

function enviarContato() {

    var nome = document.querySelector("#nomeContato").value;
    var email = document.querySelector("#emailContato").value;
    var tipoContato = document.querySelector("#Tiposdecontato").value;
    var mensagem = document.querySelector("#msg").value;

    if (email == "" || nome == "" || tipoContato == "" || mensagem == "") {
        erro.innerHTML = "Preencha todos os campos para prosseguir.";
        return false;
    }

    if (email.indexOf("@") == -1) {
        erro.innerHTML = "E-mail inválido!";
        return false;
    }

    if (tipoContato == "") {
    erro.innerHTML = "Selecione um tipo de contato.";
    return false;
}

    fetch("/contato/inserir", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nome: nome,
            email: email,
            tipoContato: tipoContato,
            mensagem: mensagem
        })
    })
    .then(resposta => {
        if (resposta.ok) {
            console.log("Mensagem enviada com sucesso!");
        } else {
            console.log("Erro ao enviar mensagem");
        }
    })
    .catch(erro => {
        console.log(erro);
    });

    return false;
}
