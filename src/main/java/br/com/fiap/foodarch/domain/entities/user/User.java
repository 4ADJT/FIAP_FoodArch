package br.com.fiap.foodarch.domain.entities.user;

import br.com.fiap.foodarch.domain.entities.user.valdations.UserCPFValidation;
import br.com.fiap.foodarch.domain.entities.user.valdations.UserEmailValidation;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

   @Builder(builderClassName = "UserBuilder")
   public User(String name, String email, String cpf, LocalDate birthdate) {

      cpf = UserCPFValidation.isValid(cpf);
      UserCPFValidation.calculatedCPFIsValid(cpf);

      UserEmailValidation.isValidEmail(email);

      this.name = name;
      this.email = email;
      this.birthdate = birthdate;
      this.cpf = cpf;
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
