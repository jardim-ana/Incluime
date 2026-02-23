var menu = document.querySelector(".menu");
var ul = document.querySelector('.ul');
var links = document.querySelectorAll(".ul a");

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