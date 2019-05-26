$(function() {
    var url = "http://localhost:8080/pessoas";
    var auth = "Basic " + btoa("admin:nimda");
    var $pessoas = $("#pessoas");

    function adicionaPessoa(pessoa) {
        $pessoas.append("" +
            "<li class='pessoa-registro'>" +
                "<ul>" +
                    "<li id='pessoa-registro' data-id='" + pessoa.id + "'>" +
                        "<p>" +
                            "<span class='label'>Nome: </span>" +
                            "<span class='nao-editar nome'>" + pessoa.nome + "</span>" +
                            "<input type='text' class='editar nome'/>" +
                        "</p>" +
                        "<p>" +
                            "<span class='label'>Sexo: </span>" +
                            "<span class='nao-editar sexo'>" + pessoa.sexo + "</span>" +
                            "<select class='editar sexo'>" +
                                "<option value='Masculino'>Masculino</option>" +
                                "<option value='Feminino'>Feminino</option>" +
                            "</select>" +
                        "</p>" +
                        "<p>" +
                            "<span class='label'>E-mail: </span>" +
                            "<span class='nao-editar email'>" + pessoa.email + "</span>" +
                            "<input type='email' class='editar email'/>" +
                        "</p>" +
                        "<p>" +
                            "<span class='label'>Data de Nascimento: </span>" +
                            "<span class='nao-editar nascimento'>" + pessoa.nascimento + "</span>" +
                            "<input id='nascimento' type='date' class='editar nascimento' value='" + pessoa.nascimento + "'/>" + // TODO: pensar em como fazer funcionar
                        "</p>" +
                        "<p>" +
                            "<span class='label'>Naturalidade: </span>" +
                            "<span class='nao-editar naturalidade'>" + pessoa.naturalidade + "</span>" +
                            "<input type='text' class='editar naturalidade'/>" +
                        "</p>" +
                        "<p>" +
                            "<span class='label'>Nacionalidade: </span>" +
                            "<span class='nao-editar nacionalidade'>" + pessoa.nacionalidade + "</span>" +
                            "<input type='text' class='editar nacionalidade'/>" +
                        "</p>" +
                        "<p>" +
                            "<span class='label'>CPF: </span>" +
                            "<span class='nao-editar cpf'>" + pessoa.cpf + "</span>" +
                            "<input type='text' class='editar cpf'/>" +
                        "</p>" +
                        "<p>" +
                            "<span class='edita-pessoa botoes nao-editar'><img src='img/edit.png'></span>" +
                            "<span data-id='{{id}}' class='remove botoes nao-editar'><img src='img/trash.png'></span>" +
                            "<span class='salvar botoes editar'><img src='img/save.png'></span>" +
                            "<span class='cancela botoes editar'><img src='img/remove.png'></span>" +
                        "</p>" +
                    "</li>" +
                "</ul>" +
            "</li>"
        );
    }

    // Load inicial dos dados
    $.ajax({
        headers: {
            "Authorization": auth
        },
        type: "GET",
        url: url,
        success: function(pessoas) {
            $.each(pessoas, function(i, pessoa) {
                adicionaPessoa(pessoa);
            });
        }
    });

    // Adição de nova pessoa
    $("#adiciona-pessoa").click(function() {
        var pessoa = {
            nome: $("#nome").val(),
            sexo: $("#sexo").val(),
            email: $("#enderecoemail").val(),
            nascimento: $("#nascimento").val(),
            naturalidade: $("#naturalidade").val(),
            nacionalidade: $("#nacionalidade").val(),
            cpf: $("#cpf").val()
        };

        $.ajax({
            headers: {
                "Authorization": auth
            },
            type: "POST",
            url: url,
            data: JSON.stringify(pessoa),
            contentType: "application/json",
            success: function(novaPessoa) {
                adicionaPessoa(novaPessoa);
            }
        });
    });

    // Remoção de uma pessoa
    $pessoas.delegate(".remove", "click", function() {
        var $li = $(this).closest("li");

        $.ajax({
            headers: {
                "Authorization": auth
            },
            type: "DELETE",
            url: url + "/" + $li.attr("data-id"),
            success: function() {
                $li.fadeOut(300, function() {
                    $(this).remove();
                });
            }
        });
    });

    // Botão de editar
    $pessoas.delegate(".edita-pessoa", "click", function() {
        var $li = $(this).closest("li");
        $li.find("input.nome").val( $li.find("span.nome").html() );
        $li.find("input.sexo").val( $li.find("span.sexo").html() );
        $li.find("input.email").val( $li.find("span.email").html() );
        $li.find("input.nascimento").val( $li.find("span.nascimento").html() );
        $li.find("input.naturalidade").val( $li.find("span.naturalidade").html() );
        $li.find("input.nacionalidade").val( $li.find("span.nacionalidade").html() );
        $li.find("input.cpf").val( $li.find("span.cpf").html() );
        $li.addClass("editar");
    });

    // Botão de cancelar edição, apenas é mostrado enquanto estiver editando um registro
    $pessoas.delegate(".cancela", "click", function() {
        $(this).closest("li").removeClass("editar");
    });

    // Salva a alteração
    $pessoas.delegate(".salvar", "click", function () {
        var $li = $(this).closest("li");
        var pessoa = {
            id: $li.attr("data-id"),
            nome: $li.find("input.nome").val(),
            sexo: $li.find("select.sexo").val(),
            email: $li.find("input.email").val(),
            nascimento: $li.find("input.nascimento").val(),
            naturalidade: $li.find("input.naturalidade").val(),
            nacionalidade: $li.find("input.nacionalidade").val(),
            cpf: $li.find("input.cpf").val()
        };

        $.ajax({
            headers: {
                "Authorization": auth
            },
            type: "PUT",
            url: url,
            data: JSON.stringify(pessoa),
            contentType: "application/json",
            success: function(novaPessoa) {
                $li.find("span.nome").html(pessoa.nome);
                $li.find("span.sexo").html(pessoa.sexo);
                $li.find("span.email").html(pessoa.email);
                $li.find("span.nascimento").html(pessoa.nascimento);
                $li.find("span.naturalidade").html(pessoa.naturalidade);
                $li.find("span.nacionalidade").html(pessoa.nacionalidade);
                $li.find("span.cpf").html(pessoa.cpf);
                $li.removeClass("editar");
            }
        });
    });

    // Busca por cpf
    $("#pesquisa").click(function(){
        var cpf = $("#cpf").val();
        var urlCpf = url;
        $(".pessoa-registro").remove();

        if(cpf != "") {
            urlCpf += "?cpf=" + cpf;
        }

        $.ajax({
            headers: {
                "Authorization": auth
            },
            type: "GET",
            url: urlCpf,
            success: function(pessoas) {
                $.each(pessoas, function(i, pessoa) {
                    adicionaPessoa(pessoa);
                });
            }
        });
    });
});