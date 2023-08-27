package xyz.fakeplex.api.common.redis.repository;

import org.springframework.stereotype.Repository;
import xyz.fakeplex.api.common.redis.type.RedisStringObject;

/**
 * @author Kyle
 */
@Repository
public interface RedisStringRepositoryInterface {

  void save(RedisStringObject redisStringObject, int timeout);

  <T extends RedisStringObject> T get(Class<T> classType, String id);

  void delete(Class<?> classType, String id);

  boolean existsInSet(Class<?> classType, String id);
}
