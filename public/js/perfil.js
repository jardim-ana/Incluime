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
