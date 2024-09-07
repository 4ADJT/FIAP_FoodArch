package br.com.fiap.foodarch.domain.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class User {
   private UUID id;

   private String name;

   private String email;

   private LocalDateTime created_at;

   private LocalDateTime updated_at;
}
