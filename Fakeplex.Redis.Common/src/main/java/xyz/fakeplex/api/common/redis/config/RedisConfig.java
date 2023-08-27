package xyz.fakeplex.api.common.redis.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Kyle
 */
@Slf4j
@Configuration
public class RedisConfig {

  private final String _configFile = "redis-config.dat";
  private JedisConnectionFactory DEFAULT;

  /** Loads Redis Connections from the configuration file */
  private void loadJedisConnections() {
    try {
      File configFile = new File(_configFile);

      if (configFile.exists()) {
        List<String> lines = Files.readAllLines(configFile.toPath(), Charset.defaultCharset());

        for (String line : lines) deserializeConnection(line);

        return;
      }

      log.error("Could not find redis configuration file : " + _configFile);
    } catch (Exception exception) {
      exception.printStackTrace();
      log.error("Could not parse redis configuration file : " + _configFile);
    }
  }

  /**
   * Deserializes the specified string into a JedisConnectionFactory object
   *
   * @param line The string to deserialize
   */
  private void deserializeConnection(String line) {
    String[] args = line.split(" ");

    if (args.length == 4) {
      String address = args[0];
      int port = args[1].matches("^(?=.*[1-9])\\\\d+$") ? Integer.parseInt(args[1]) : 6379;
      String type = args[2];
      String name = args[3];

      log.info(
          "RedisStandaloneConfiguration("
              + _configFile
              + ") : "
              + address
              + " "
              + port
              + " "
              + type
              + " "
              + name);

      if (type.equalsIgnoreCase("MASTER") && name.equalsIgnoreCase("DefaultConnection"))
        DEFAULT = new JedisConnectionFactory(new RedisStandaloneConfiguration(address, port));
    }
  }

  /**
   * Returns a JedisConnectionFactory object that represents the redis database If the redis
   * information isn't configured, this method returns null
   *
   * @return A JedisConnectionFactory object that provides access to the redis database
   */
  public JedisConnectionFactory getDefaultConnection() {
    if (DEFAULT == null) loadJedisConnections();

    return DEFAULT;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());

    template.setDefaultSerializer(new StringRedisSerializer());
    template.setStringSerializer(new GsonRedisSerializer<>());

    return template;
  }

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory connectionFactory = getDefaultConnection();

    if (connectionFactory == null) {
      log.error("Could not find 'DefaultConnection' in " + _configFile);
      throw new RuntimeException("Invalid configuration file: " + _configFile);
    }

    return connectionFactory;
  }

  private static class GsonRedisSerializer<T> implements RedisSerializer<T> {

    private final Gson gson =
        new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();

    @Override
    public byte[] serialize(T t) throws SerializationException {
      return gson.toJson(t).getBytes();
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
      if (bytes == null || bytes.length == 0) {
        return null;
      }
      return gson.fromJson(new String(bytes), getType());
    }

    private Type getType() {
      return new TypeToken<T>() {}.getType();
    }
  }
}
