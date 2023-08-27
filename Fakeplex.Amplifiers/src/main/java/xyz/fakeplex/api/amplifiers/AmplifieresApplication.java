package xyz.fakeplex.api.amplifiers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author Kyle
 */
@EnableRedisRepositories(basePackages = "xyz.fakeplex.api.common.redis")
@EnableJpaRepositories(basePackages = {"xyz.fakeplex.api.common.database"})
@SpringBootApplication(scanBasePackages = {"xyz.fakeplex.api"})
public class AmplifieresApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(AmplifieresApplication.class);
    app.run(args);
  }
}
