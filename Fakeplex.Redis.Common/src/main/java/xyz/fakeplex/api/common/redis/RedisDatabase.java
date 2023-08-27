package xyz.fakeplex.api.common.redis;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.fakeplex.api.common.redis.repository.RedisHashRepository;
import xyz.fakeplex.api.common.redis.repository.RedisHashRepositoryInterface;
import xyz.fakeplex.api.common.redis.repository.RedisStringRepository;
import xyz.fakeplex.api.common.redis.repository.RedisStringRepositoryInterface;

/**
 * @author Kyle
 */
@Getter
@Component
public class RedisDatabase {

  private final RedisHashRepositoryInterface redisHashRepository;
  private final RedisStringRepositoryInterface redisStringRepository;

  @Autowired
  public RedisDatabase(
      RedisHashRepository redisHashRepository, RedisStringRepository redisStringRepository) {
    this.redisHashRepository = redisHashRepository;
    this.redisStringRepository = redisStringRepository;
  }
}
