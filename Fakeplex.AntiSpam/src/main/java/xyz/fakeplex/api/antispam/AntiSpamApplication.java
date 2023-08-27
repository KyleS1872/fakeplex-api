package xyz.fakeplex.api.antispam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Kyle
 */
@EnableJpaRepositories(basePackages = {"xyz.fakeplex.api"})
@SpringBootApplication(scanBasePackages = {"xyz.fakeplex.api"})
public class AntiSpamApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(AntiSpamApplication.class);
    app.run(args);
  }
}
