package xyz.fakeplex.api.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Kyle
 */
@EnableJpaRepositories(basePackages = {"xyz.fakeplex.api"})
@SpringBootApplication(scanBasePackages = {"xyz.fakeplex.api"})
public class AccountsApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(AccountsApplication.class);
    app.run(args);
  }
}
