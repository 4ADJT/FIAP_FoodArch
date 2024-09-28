package br.com.fiap.foodarch.infra.config.converters;

import com.fasterxml.jackson.core.JsonFactory;
// import com.fasterxml.jackson.core.JsonParser; @deprecated
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

  @Bean
  public JsonFactory jsonFactory() {
    JsonFactory factory = new JsonFactory();
    factory.enable(JsonReadFeature.ALLOW_TRAILING_COMMA.mappedFeature());
    return factory;
  }

  @Bean
  public ObjectMapper objectMapper(JsonFactory jsonFactory) {
    ObjectMapper mapper = new ObjectMapper(jsonFactory);
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return mapper;
  }

}
