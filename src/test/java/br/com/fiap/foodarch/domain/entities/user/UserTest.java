package br.com.fiap.foodarch.domain.entities.user;

import br.com.fiap.foodarch.domain.entities.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  private User user;

  @BeforeEach
  void setUp() {
    user = new User();
  }

  @Test
  @DisplayName("Deve criar um usuário com dados válidos")
  void testUserBuilderWithValidData() {
    // Dados válidos de CPF Brasileiro (exemplo fictício)
    String validCpf = "52998224725"; // CPF válido para testes

    // Dados válidos de email e data de nascimento
    String validEmail = "rodrigo@example.com";
    LocalDate validBirthdate = LocalDate.of(1987, 5, 15);

    // Criação do usuário usando o builder
    User user = User.builder()
        .name("Rodrigo Brocchi")
        .email(validEmail)
        .cpf(validCpf)
        .birthdate(validBirthdate)
        .build();

    // Asserções
    assertNotNull(user);
    assertEquals("Rodrigo Brocchi", user.getName());
    assertEquals(validEmail, user.getEmail());
    assertEquals(validCpf, user.getCpf());
    assertEquals(validBirthdate, user.getBirthdate());
  }

  @Test
  @DisplayName("Deve lançar exceção ao criar usuário com CPF inválido")
  void testUserBuilderWithInvalidCPF() {
    // CPF inválido (exemplo fictício)
    String invalidCpf = "12345678900";

    // Dados válidos de email e data de nascimento
    String validEmail = "rodrigo@example.com";
    LocalDate validBirthdate = LocalDate.of(1987, 5, 15);

    // Expectativa: lançamento de exceção ao validar CPF
    assertThatThrownBy(() -> User.builder()
        .name("Rodrigo Brocchi")
        .email(validEmail)
        .cpf(invalidCpf)
        .birthdate(validBirthdate)
        .build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("CPF is not valid.");
  }

  @Test
  @DisplayName("Deve lançar exceção ao criar usuário com email inválido")
  void testUserBuilderWithInvalidEmail() {
    // Dados válidos de CPF Brasileiro (exemplo fictício)
    String validCpf = "52998224725";

    // Email inválido
    String invalidEmail = "rodrigo@.com";

    // Dados válidos de data de nascimento
    LocalDate validBirthdate = LocalDate.of(1987, 5, 15);

    // Expectativa: lançamento de exceção ao validar email
    assertThatThrownBy(() -> User.builder()
        .name("Rodrigo Brocchi")
        .email(invalidEmail)
        .cpf(validCpf)
        .birthdate(validBirthdate)
        .build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid email");
  }

  @Test
  @DisplayName("Deve lançar exceção ao criar usuário com data de nascimento inválida")
  void testUserBuilderWithInvalidBirthdate() {
    // Dados válidos de CPF Brasileiro (exemplo fictício)
    String validCpf = "52998224725";

    // Dados válidos de email
    String validEmail = "rodrigo@example.com";

    // Data de nascimento inválida (exemplo: futura)
    LocalDate invalidBirthdate = LocalDate.now().plusDays(1);

    // Expectativa: lançamento de exceção ao validar data de nascimento
    assertThatThrownBy(() -> User.builder()
        .name("Rodrigo Brocchi")
        .email(validEmail)
        .cpf(validCpf)
        .birthdate(invalidBirthdate)
        .build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Birthdate cannot be in the future");
  }

  @Test
  @DisplayName("Deve considerar dois usuários iguais se tiverem o mesmo ID")
  void testEqualsAndHashCode() {
    UUID id = UUID.randomUUID();
    User user1 = new User(id, "Rodrigo Brocchi", "rodrigo@example.com",
        LocalDate.of(1987, 5, 15), "52998224725", LocalDateTime.now(), LocalDateTime.now());
    User user2 = new User(id, "Rodrigo Brocchi", "rodrigo@example.com",
        LocalDate.of(1987, 5, 15), "52998224725", LocalDateTime.now(), LocalDateTime.now());

    assertEquals(user1, user2, "Usuários com o mesmo ID devem ser iguais");
    assertEquals(user1.hashCode(), user2.hashCode(), "HashCodes devem ser iguais para usuários com o mesmo ID");
  }

  @Test
  @DisplayName("Deve testar getters e setters")
  void testUserSettersAndGetters() {
    UUID id = UUID.randomUUID();
    String name = "Rodrigo Brocchi";
    String email = "rodrigo@example.com";
    LocalDate birthdate = LocalDate.of(1987, 5, 15);
    String cpf = "52998224725";
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime updatedAt = LocalDateTime.now();

    user.setId(id);
    user.setName(name);
    user.setEmail(email);
    user.setBirthdate(birthdate);
    user.setCpf(cpf);
    user.setCreatedAt(createdAt);
    user.setUpdatedAt(updatedAt);

    assertEquals(id, user.getId());
    assertEquals(name, user.getName());
    assertEquals(email, user.getEmail());
    assertEquals(birthdate, user.getBirthdate());
    assertEquals(cpf, user.getCpf());
    assertEquals(createdAt, user.getCreatedAt());
    assertEquals(updatedAt, user.getUpdatedAt());
  }
}