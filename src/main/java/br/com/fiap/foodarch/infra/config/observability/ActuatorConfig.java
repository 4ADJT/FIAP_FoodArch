package br.com.fiap.foodarch.infra.config.observability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Configuration
public class ActuatorConfig {
  private final DataSource dataSource;

  @Autowired
  public ActuatorConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  public HealthIndicator customHealthIndicator() {
    return () -> {
      boolean isHealthy = checkServiceHealth();
      if (isHealthy) {
        return Health.up().withDetail("status", "All systems operational").build();
      } else {
        return Health.down().withDetail("status", "Service is down").build();
      }
    };
  }

  @Bean
  public InfoContributor customInfoContributor() {
    return builder -> {
      builder.withDetail("app", Map.of("name", "FoodArch", "version", "1.0.0"));
      builder.withDetail("description", "FoodArch is a food delivery platform.");
    };
  }

  private boolean checkServiceHealth() {
    try (Connection connection = dataSource.getConnection()) {
      return connection.isValid(2);
    } catch (SQLException e) {
      return false;
    }
  }
}
