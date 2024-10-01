package br.com.fiap.foodarch.bdd;

import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StepDefinition {

  private Response response;

  @Quando("eu faço um POST para o endpoint users com o nome {string}, email {string}, data de nascimento {string} e CPF {string}")
  public void eu_faço_um_post_para_o_endpoint_users(String nome, String email, String birthdate, String cpf) {
    // Criar o input para o POST baseado no UserInput
    String body = "{ " +
        "\"name\": \"" + nome + "\"," +
        "\"email\": \"" + email + "\"," +
        "\"birthdate\": \"" + birthdate + "\"," +
        "\"cpf\": \"" + cpf + "\"" +
        "}";

    // Fazendo o POST
    response = RestAssured
        .given()
        .header("Content-Type", "application/json")
        .body(body)
        .when()
        .post("/users")  // Ajuste para o seu endpoint real
        .then()
        .extract()
        .response();
  }

  @Então("o código de resposta deve ser {int}")
  public void o_código_de_resposta_deve_ser(Integer statusCode) {
    assertEquals(statusCode.intValue(), response.getStatusCode());
  }

  @Então("o id do usuário deve ser um UUID")
  public void o_id_do_usuário_deve_ser_um_uuid() {
    if (response.getStatusCode() == 201) {
      // Extraindo o ID do JSON de resposta
      String userId = response.jsonPath().getString("id");
      try {
        UUID.fromString(userId);  // Tenta converter o id para UUID
      } catch (IllegalArgumentException e) {
        fail("ID não é um UUID válido");
      }
    }
  }

  @Então("o nome retornado deve ser {string}")
  public void o_nome_retornado_deve_ser(String nomeEsperado) {
    if (response.getStatusCode() == 201) {
      String nomeRetornado = response.jsonPath().getString("name");
      assertEquals(nomeEsperado, nomeRetornado);
    }
  }

  @Então("o email retornado deve ser {string}")
  public void o_email_retornado_deve_ser(String emailEsperado) {
    if (response.getStatusCode() == 201) {
      String emailRetornado = response.jsonPath().getString("email");
      assertEquals(emailEsperado, emailRetornado);
    }
  }

  @Então("a data de nascimento retornada deve ser {string}")
  public void a_data_de_nascimento_retornada_deve_ser(String dataNascimentoEsperada) {
    if (response.getStatusCode() == 201) {
      String dataNascimentoRetornada = response.jsonPath().getString("birthdate");
      assertEquals(dataNascimentoEsperada, dataNascimentoRetornada);
    }
  }

  @Então("o CPF retornado deve ser {string}")
  public void o_cpf_retornado_deve_ser(String cpfEsperado) {
    if (response.getStatusCode() == 201) {
      String cpfRetornado = response.jsonPath().getString("cpf");
      assertEquals(cpfEsperado, cpfRetornado);
    }
  }

  @Então("a mensagem de erro deve ser {string}")
  public void a_mensagem_de_erro_deve_ser(String mensagemEsperada) {
    if (response.getStatusCode() == 400) {
      String mensagemErro = response.jsonPath().getString("message");
      assertEquals(mensagemEsperada, mensagemErro);
    }
  }
}
