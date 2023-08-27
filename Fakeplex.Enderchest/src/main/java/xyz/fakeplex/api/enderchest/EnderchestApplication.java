package xyz.fakeplex.api.enderchest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyle
 */
@SpringBootApplication(scanBasePackages = {"xyz.fakeplex.api"})
public class EnderchestApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(EnderchestApplication.class);
    Map<String, Object> properties = new HashMap<>();
    properties.put(
        "maps",
        (System.getProperty("os.name").startsWith("Windows")
                ? "C:"
                : File.separator + "home" + File.separator + "mineplex")
            + File.separator
            + "update"
            + File.separator
            + "maps"
            + File.separator);
    app.setDefaultProperties(properties);
    app.run(args);
  }
}
