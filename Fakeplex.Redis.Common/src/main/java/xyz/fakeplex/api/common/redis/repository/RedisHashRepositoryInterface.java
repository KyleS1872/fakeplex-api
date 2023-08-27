package xyz.fakeplex.api.common.redis.repository;

import org.springframework.stereotype.Repository;
import xyz.fakeplex.api.common.redis.type.RedisHashObject;

/**
 * @author Kyle
 */
@Repository
public interface RedisHashRepositoryInterface {

  void save(RedisHashObject redisHashObject);

  <T extends RedisHashObject> T get(Class<T> classType, String id);

  void delete(Class<?> classType, String id);

  boolean existsInSet(Class<?> classType, String id);
}
