package xyz.fakeplex.api.common.redis.message;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Kyle
 */
@Component
public class RedisMessage {

  private final RedisTemplate<String, Object> redisTemplate;

  public RedisMessage(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void publishMessage(String channel, Object message) {
    redisTemplate.convertAndSend(channel, message);
  }
}
