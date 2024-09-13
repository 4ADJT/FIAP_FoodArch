package br.com.fiap.foodarch.domain.entities.user;

import java.time.LocalDate;

class UserTest {

  private final LocalDate now = LocalDate.parse("2000-01-01");
  public final LocalDate incrementedDay = LocalDate.now().plusDays(1);

//  @Test
//  void UserCPFIsNotValid() {
//
//    Assertions.assertThrows(IllegalArgumentException.class, () -> {
//      new User
//          .UserBuilder()
//          .name("John Doe")
//          .email("test@test.com")
//          .birthdate(now)
//          .cpf("12312312322")
//          .build();
//    });
//  }
//
//  @Test
//  void UserEmailIsNotValid() {
//
//    Assertions.assertThrows(IllegalArgumentException.class, () -> {
//      new User
//          .UserBuilder()
//          .name("John Doe")
//          .email(null)
//          .birthdate(now)
//          .cpf("41093396008")
//          .build();
//    });
//
//  }
//
//  @Test
//  void createUserWithFactory() {
//    UserFactory userFactory = new UserFactory();
//    User user = userFactory.createUser("John Doe", "test@test.com", now, "18553980014");
//
//    Assertions.assertEquals("John Doe", user.getName());
//
//  }

}
