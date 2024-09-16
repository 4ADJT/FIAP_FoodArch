package br.com.fiap.foodarch.domain.entities.users;

import br.com.fiap.foodarch.domain.entities.users.valdations.UserBirthdateValidation;
import br.com.fiap.foodarch.domain.entities.users.valdations.UserCPFValidation;
import br.com.fiap.foodarch.domain.entities.users.valdations.UserEmailValidation;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
   private UUID id;

   private String name;

   private String email;

   private LocalDate birthdate;

   private String cpf;

   private LocalDateTime createdAt;

   private LocalDateTime updatedAt;

   @Builder(builderClassName = "CreateUserBuilder")
   public User(String name, String email, String cpf, LocalDate birthdate) {

      cpf = cpf.replaceAll("\\D", "").trim();

      UserCPFValidation.isValid(cpf);
      UserCPFValidation.calculatedCPFIsValid(cpf);
      UserEmailValidation.isValidEmail(email);
      UserBirthdateValidation.isValidBirthdate(birthdate);

      this.name = name;
      this.email = email;
      this.birthdate = birthdate;
      this.cpf = cpf;
   }

   @Builder(builderClassName = "UpdateUserBuilder")
   public User(UUID id, String name, String email, String cpf, LocalDate birthdate, LocalDateTime createdAt) {

      cpf = cpf.replaceAll("\\D", "").trim();

      UserCPFValidation.isValid(cpf);
      UserCPFValidation.calculatedCPFIsValid(cpf);
      UserEmailValidation.isValidEmail(email);
      UserBirthdateValidation.isValidBirthdate(birthdate);

      this.id = id;
      this.name = name;
      this.email = email;
      this.birthdate = birthdate;
      this.cpf = cpf;
      this.createdAt = createdAt;

   }

   @PrePersist
   protected void onCreate() {
      createdAt = LocalDateTime.now();
      updatedAt = LocalDateTime.now();
   }

   @PreUpdate
   protected void onUpdate() {
      updatedAt = LocalDateTime.now();
   }
}
