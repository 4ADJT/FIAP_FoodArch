package br.com.fiap.foodarch.bdd.definitions;

import br.com.fiap.foodarch.domain.records.users.UserInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UsersStepDefinitions {
  private final ObjectMapper objectMapper;
  private Response response;
  private String generatedEmail;
  private String generatedCpf;

  public UsersStepDefinitions() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new JavaTimeModule());
  }

  // Método para gerar um CPF válido
  private String generateCpf() {
    Random random = new Random();
    int[] digits = new int[9];
    for (int i = 0; i < 9; i++) {
      digits[i] = random.nextInt(10);
    }

    int d1 = 0;
    int d2 = 0;
    for (int i = 0; i < 9; i++) {
      d1 += digits[i] * (10 - i);
      d2 += digits[i] * (11 - i);
    }

    d1 = 11 - (d1 % 11);
    d2 += d1 * 2;
    d2 = 11 - (d2 % 11);

    if (d1 >= 10) d1 = 0;
    if (d2 >= 10) d2 = 0;

    return String.format("%d%d%d%d%d%d%d%d%d%d%d",
        digits[0], digits[1], digits[2],
        digits[3], digits[4], digits[5],
        digits[6], digits[7], digits[8],
        d1, d2);
  }

  // Método para gerar um e-mail único a cada execução
  private String generateEmail(String baseName) {
    return baseName.toLowerCase() + "+" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
  }

  @Quando("eu faço um POST para o endpoint users com o nome {string} e data de nascimento {string} que é o usuário novo")
  public void eu_faço_um_post_para_o_endpoint_users(String nome, String birthdate) {
    try {
      generatedEmail = generateEmail(nome);
      generatedCpf = generateCpf();

      LocalDate dataDeNascimento = LocalDate.parse(birthdate);
      // Criando o UserInput
      UserInput userInput = new UserInput(
          nome,
          generatedEmail,
          dataDeNascimento,
          generatedCpf
      );

      // Serializando o objeto UserInput para JSON
      String body = objectMapper.writeValueAsString(userInput);

      // Fazendo o POST com o JSON gerado
      response = RestAssured
          .given()
          .header("Content-Type", "application/json")
          .body(body)
          .when()
          .post("http://localhost:8080/users")  // Ajuste para o seu endpoint real
          .then()
          .extract()
          .response();
    } catch (Exception e) {
      e.printStackTrace();
      fail("Erro ao converter UserInput para JSON");
    }
  }

  @Então("o código de resposta do usuário novo deve ser {int}")
  public void o_código_de_resposta_do_usuário_novo_deve_ser(Integer statusCode) {
    assertEquals(statusCode.intValue(), response.getStatusCode());
  }

  @Então("o id do usuário novo deve ser um UUID")
  public void o_id_do_usuário_novo_deve_ser_um_uuid() {
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

  @Então("o nome do usuário novo retornado deve ser {string}")
  public void o_nome_do_usuário_novo_retornado_deve_ser(String nomeEsperado) {
    if (response.getStatusCode() == 201) {
      String nomeRetornado = response.jsonPath().getString("name");
      assertEquals(nomeEsperado, nomeRetornado);
    }
  }

  @Então("o email do usuário novo retornado deve ser o gerado")
  public void o_email_do_usuário_novo_retornado_deve_ser_o_gerado() {
    if (response.getStatusCode() == 201) {
      String emailRetornado = response.jsonPath().getString("email");
      assertEquals(generatedEmail, emailRetornado);
    }
  }

  @Então("a data de nascimento do usuário novo retornada deve ser {string}")
  public void a_data_de_nascimento_do_usuário_novo_retornada_deve_ser(String dataNascimentoEsperada) {
    if (response.getStatusCode() == 201) {
      String dataNascimentoRetornada = response.jsonPath().getString("birthdate");
      assertEquals(dataNascimentoEsperada, dataNascimentoRetornada);
    }
  }

  @Então("o CPF do usuário novo retornado deve ser o gerado")
  public void o_cpf_do_usuário_novo_retornado_deve_ser_o_gerado() {
    if (response.getStatusCode() == 201) {
      String cpfRetornado = response.jsonPath().getString("cpf");
      assertEquals(generatedCpf, cpfRetornado);
    }
  }
}
