package br.com.fiap.foodarch.bdd.definitions;

import br.com.fiap.foodarch.domain.records.restaurants.RestaurantInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RestaurantStepDefinitions {
  private final ObjectMapper objectMapper;
  private Response response;
  private String ownerId;

  public RestaurantStepDefinitions() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new JavaTimeModule());
  }

  @Dado("que eu obtenha um usuário existente para o restaurante")
  public void que_eu_obtenha_um_usuário_existente_para_o_restaurante() {
    response = RestAssured
        .given()
        .contentType("application/json")
        .when()
        .get("http://localhost:8080/users")  // Ajuste para o seu endpoint real
        .then()
        .extract()
        .response();

    ownerId = response.jsonPath().getString("content[0].id");
  }

  @Quando("eu faço um POST para o endpoint de restaurante com o nome {string}")
  public void eu_faço_um_post_para_o_endpoint_de_restaurante_com_o_nome(String restaurantName) {
    try {
      // Criando o objeto RestaurantInput com ownerId e restaurantName
      RestaurantInput restaurantInput = new RestaurantInput(restaurantName, UUID.fromString(ownerId));

      // Serializando o objeto RestaurantInput para JSON
      String body = objectMapper.writeValueAsString(restaurantInput);

      // Fazendo o POST com o JSON gerado e o ownerId no path
      response = RestAssured
          .given()
          .header("Content-Type", "application/json")
          .body(body)
          .when()
          .post("http://localhost:8080/restaurants/" + ownerId)
          .then()
          .extract()
          .response();
    } catch (Exception e) {
      e.printStackTrace();
      fail("Erro ao converter RestaurantInput para JSON");
    }
  }

  @Então("o código de resposta do restaurante deve ser {int}")
  public void o_código_de_resposta_do_restaurante_deve_ser(Integer statusCode) {
    assertEquals(statusCode.intValue(), response.getStatusCode());
  }

  @Então("o nome do restaurante retornado deve ser {string}")
  public void o_nome_do_restaurante_retornado_deve_ser(String nomeEsperado) {
    if (response.getStatusCode() == 201) {
      String nomeRetornado = response.jsonPath().getString("name");
      assertEquals(nomeEsperado, nomeRetornado);
    }
  }

  @Então("o ID do restaurante deve ser um UUID válido")
  public void o_id_do_restaurante_deve_ser_um_uuid_válido() {
    if (response.getStatusCode() == 201) {
      String restaurantId = response.jsonPath().getString("id");
      try {
        UUID.fromString(restaurantId);
      } catch (IllegalArgumentException e) {
        fail("ID do restaurante não é um UUID válido");
      }
    }
  }
}
