var usuarioModel = require("../models/usuarioModel");

function autenticar(req, res) {
    var email = req.body.email;
    var senha = req.body.senha;

    if (email == undefined) {
        res.status(400).send("Seu email está undefined!");
    } else if (senha == undefined) {
        res.status(400).send("Sua senha está indefinida!");
    } else {

        usuarioModel.autenticar(email, senha)
            .then(
                function (resultadoAutenticar) {
                    console.log(`\nResultados encontrados: ${resultadoAutenticar.length}`);
                    console.log(`Resultados: ${JSON.stringify(resultadoAutenticar)}`); // transforma JSON em String

                    if (resultadoAutenticar.length == 1) {
                        console.log(resultadoAutenticar);

                        res.json({
                            id: resultadoAutenticar[0].id,
                            nome: resultadoAutenticar[0].nome,
                            email: resultadoAutenticar[0].email,
                            sobrenome: resultadoAutenticar[0].sobrenome,
                            escola: resultadoAutenticar[0].nome_escola,
                            tipo_usuario: resultadoAutenticar[0].tipo_usuario
                        })

                    } else if (resultadoAutenticar.length == 0) {
                        res.status(403).send("Email e/ou senha inválido(s)");
                    } else {
                        res.status(403).send("Mais de um usuário com o mesmo login e senha!");
                    }
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log("\nHouve um erro ao realizar o login! Erro: ", erro.sqlMessage);
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }

}

function atualizar(req, res) {

    var id = req.body.id;
    var email = req.body.email;
    var senha = req.body.senha;

    let campos = [];

    // Validações
    if (id == undefined) {
        res.status(400).send("ID do usuário está undefined!");
        return;
    }

    // Monta os campos dinamicamente
    if (email != undefined) {
        campos.push(`email = '${email}'`);
    }

    if (senha != undefined) {
        campos.push(`senha = '${senha}'`);
    }

    // Caso nenhum campo tenha sido enviado
    if (campos.length == 0) {
        res.status(400).send("Nenhum campo enviado para atualização!");
        return;
    }

    usuarioModel.atualizar(id, campos)
        .then(function (resultado) {
            res.json(resultado);
        })
        .catch(function (erro) {

            console.log(erro);
            console.log(
                "\nHouve um erro ao atualizar o usuário! Erro: ",
                erro.sqlMessage
            );

            res.status(500).json(erro.sqlMessage);
        });

}

module.exports = {
    autenticar,
    atualizar
}