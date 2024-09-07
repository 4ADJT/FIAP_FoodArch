package br.com.fiap.foodarch;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(servers = { @Server(url = "/", description = "Default Server URL") })
public class FoodarchApplication {

  public static void main(String[] args) {
    SpringApplication.run(FoodarchApplication.class, args);
  }

}
