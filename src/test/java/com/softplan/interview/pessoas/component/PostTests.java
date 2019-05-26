package com.softplan.interview.pessoas.component;


import com.softplan.interview.pessoas.BaseTest;
import com.softplan.interview.pessoas.model.Pessoa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
public class PostTests extends BaseTest {

    @Test
    public void verificaPessoaValidaComTodosCamposPreenchidos() {
        given()
            .auth().basic("admin", "nimda")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(getPessoa("98487897070"))
        .when()
            .post("/pessoas")
        .then()
            .statusCode(200);
    }

    @Test
    public void verificaPessoaValidaComCamposObrigatoriosPreenchidos() {
        Pessoa pessoa = getPessoa("37769139082");
        pessoa.setNaturalidade(null);
        pessoa.setEmail(null);
        pessoa.setNacionalidade(null);
        pessoa.setSexo(null);

        given()
            .auth().basic("admin", "nimda")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
            .statusCode(200);
    }

    @Test
    public void verifyInvalidAuthentication() {
        given()
            .auth().basic("novalid", "novalid")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(getPessoa("32439826006"))
        .when()
            .post("/pessoas")
        .then()
            .statusCode(401);
    }

    private Pessoa getPessoa(String cpf) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Julio");
        pessoa.setSexo("Masculino");
        pessoa.setCpf(cpf);
        pessoa.setEmail("julio@nagaita.com");
        pessoa.setNacionalidade("Brasileira");
        pessoa.setNaturalidade("Fazendinha");
        pessoa.setNascimento(LocalDate.of(1997, 9, 12));
        return pessoa;
    }

}
