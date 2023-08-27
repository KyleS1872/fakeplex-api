package xyz.fakeplex.api.banner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kyle
 */
@SpringBootApplication(scanBasePackages = {"xyz.fakeplex.api"})
public class BannerApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(BannerApplication.class);
    app.run(args);
  }
}
