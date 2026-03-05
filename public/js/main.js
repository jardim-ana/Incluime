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
var menu = document.querySelector(".menu");
var ul = document.querySelector('.ul');
var links = document.querySelectorAll(".ul a");
var video = document.querySelectorAll(".clique");
var logo = document.querySelectorAll(".conecta")
var botaoVideo = document.getElementById("verVideo");


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



