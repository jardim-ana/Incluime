var menu = document.querySelector(".menu");
var ul = document.querySelector('.ul')

menu.addEventListener('click', ()=>{
  
    if (ul.classList.contains('ativo')) {
        ul.classList.remove('ativo');
        document.querySelector('.menu img').src = 'assets/icon/menu.png'
    }
    else {
        ul.classList.add('ativo');
        document.querySelector('.menu img').src = 'assets/icon/close.png'
    }
})