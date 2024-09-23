package br.com.fiap.foodarch.infra.config.observability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DatabaseHealthCheckService {
  private final DataSource dataSource;

  @Autowired
  public DatabaseHealthCheckService(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  private static final Logger logger = LoggerFactory.getLogger(DatabaseHealthCheckService.class);

  @Scheduled(fixedRate = 300000)
  public void checkDatabaseConnection() {
    try (Connection connection = dataSource.getConnection()) {
      if (connection.isValid(20)) {
        logger.info("\n - Database connection is healthy. \n");
      } else {
        logger.warn("\n - Database connection is not healthy. \n");
      }
    } catch (SQLException e) {
      logger.error("\n - Error checking database connection: ", e);
    }
  }
}
