package br.com.fiap.foodarch.infra.config.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  private final ObjectMapper objectMapper;

  public JacksonConfig(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @PostConstruct
  public void customizeObjectMapper() {
    SimpleModule module = new SimpleModule();

    objectMapper.registerModule(module);

    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

  }
}