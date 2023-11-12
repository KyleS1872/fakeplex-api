package xyz.fakeplex.api.common.redis.repository;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import xyz.fakeplex.api.common.redis.annotation.RedisString;
import xyz.fakeplex.api.common.redis.type.RedisStringObject;
import xyz.fakeplex.api.common.redis.util.RedisUtils;

/**
 * @author Kyle
 */
@Slf4j
@Component
public class RedisStringRepository implements RedisStringRepositoryInterface {

  private final RedisTemplate<String, String> redisTemplate;
  private final ValueOperations<String, String> valueOperations;
  private final ZSetOperations<String, String> setOperations;

  public RedisStringRepository(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
    valueOperations = redisTemplate.opsForValue();
    setOperations = redisTemplate.opsForZSet();
  }

  @Override
  public void save(RedisStringObject redisStringObject, int timeout) {
    String json = RedisUtils.gson.toJson(redisStringObject);

    if (json.isEmpty()) return;

    String keySpace = getKeySpace(redisStringObject.getClass());
    String key = (!keySpace.isEmpty() ? keySpace + "." : "") + redisStringObject.getIdentifier();

    valueOperations.set(key, json);
    setOperations.add(
        keySpace,
        redisStringObject.getIdentifier(),
        ((double) System.currentTimeMillis() / 1000) + timeout);
  }

  @Override
  public <T extends RedisStringObject> T get(Class<T> classType, String id) {
    try {
      String keySpace = getKeySpace(classType);
      String key = (!keySpace.isEmpty() ? keySpace + "." : "") + id;

      if (setOperations.rank(keySpace, id) == null) return null;

      String json = valueOperations.get(key);
      JSONObject jsonObject = new JSONObject(json);
      return RedisUtils.gson.fromJson(jsonObject.toString(), classType);
    } catch (Exception e) {
      log.debug(e.getMessage());
      return null;
    }
  }

  @Override
  public void delete(Class<?> classType, String id) {
    String keySpace = getKeySpace(classType);
    String key = (!keySpace.isEmpty() ? keySpace + "." : "") + id;

    redisTemplate.delete(key);
    setOperations.remove(keySpace, id);
  }

  @Override
  public boolean existsInSet(Class<?> classType, String id) {
    String keySpace = getKeySpace(classType);
    return setOperations.rank(keySpace, id) != null;
  }

  private String getKeySpace(Class<?> classType) {
    RedisString redisStringAnnotation = classType.getAnnotation(RedisString.class);
    return redisStringAnnotation != null ? redisStringAnnotation.value() : "";
  }
}
