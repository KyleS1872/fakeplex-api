package xyz.fakeplex.api.chatfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kyle
 */
@SpringBootApplication(scanBasePackages = {"xyz.fakeplex.api"})
public class ChatFilterApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ChatFilterApplication.class);
    app.run(args);
  }
}
