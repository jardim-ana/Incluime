document.getElementById('abrir_icon').addEventListener('click', function () {
    document.getElementById('sidebar').classList.toggle('open-sidebar');
});

var logoutButton = document.getElementById("logout_button");

    logoutButton.addEventListener("click", function () {
        var confirmarSaida = confirm("Tem certeza que deseja sair?");

        if (confirmarSaida) {
            window.location.href = "index.html";
        }
    });

var escolas = [
    "Escola Estadual João Silva",
    "Escola Municipal Machado de Assis",
    "Colégio Objetivo",
    "Etec de São Paulo",
    "Escola Estadual Paulo Freire"
  ];

  var inputPerfil = document.getElementById("escolaPerfilInput");
  var sugestoesPerfil = document.getElementById("sugestoesPerfil");

  inputPerfil.addEventListener("input", function () {
    var valor = inputPerfil.value.toLowerCase();
    sugestoesPerfil.innerHTML = "";

    if (valor === "") {
      return;
    }

    for (var i = 0; i < escolas.length; i++) {
      if (escolas[i].toLowerCase().indexOf(valor) !== -1) {
        var li = document.createElement("li");
        li.textContent = escolas[i];

        li.onclick = function () {
          inputPerfil.value = this.textContent;
          sugestoesPerfil.innerHTML = "";
        };

        sugestoesPerfil.appendChild(li);
      }
    }
  });