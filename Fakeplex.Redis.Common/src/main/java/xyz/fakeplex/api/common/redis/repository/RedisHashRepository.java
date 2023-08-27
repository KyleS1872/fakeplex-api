package xyz.fakeplex.api.common.redis.repository;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;
import xyz.fakeplex.api.common.redis.type.RedisHashObject;

/**
 * @author Kyle
 */
@Slf4j
@Component
public class RedisHashRepository implements RedisHashRepositoryInterface {

  private final Gson gson;

  private final HashOperations<String, String, String> hashOperations;
  private final SetOperations<String, Object> setOperations;

  public RedisHashRepository(RedisTemplate<String, Object> redisTemplate, Gson gson) {
    hashOperations = redisTemplate.opsForHash();
    setOperations = redisTemplate.opsForSet();
    this.gson = gson;
  }

  @Override
  public void save(RedisHashObject redisHashObject) {
    String json = gson.toJson(redisHashObject);
    JSONObject jsonObject = new JSONObject(json);

    if (jsonObject.isEmpty()) return;

    String keySpace = getKeySpace(redisHashObject.getClass());
    String key = (!keySpace.isEmpty() ? keySpace + "." : "") + redisHashObject.getIdentifier();

    jsonObject.keySet().forEach(mk -> hashOperations.put(key, mk, jsonObject.get(mk).toString()));

    setOperations.add(keySpace, redisHashObject.getIdentifier());
  }

  @Override
  public <T extends RedisHashObject> T get(Class<T> classType, String id) {
    try {
      String keySpace = getKeySpace(classType);
      String key = (!keySpace.isEmpty() ? keySpace + "." : "") + id;

      if (Boolean.FALSE.equals(setOperations.isMember(keySpace, id))) return null;

      JSONObject jsonObject = new JSONObject(hashOperations.entries(key));
      return gson.fromJson(jsonObject.toString(), classType);
    } catch (Exception e) {
      log.debug(e.getMessage());
      return null;
    }
  }

  @Override
  public void delete(Class<?> classType, String id) {
    String keySpace = getKeySpace(classType);
    String key = (!keySpace.isEmpty() ? keySpace + "." : "") + id;

    hashOperations.delete(key, hashOperations.keys(key).toArray());

    setOperations.remove(keySpace, id);
  }

  @Override
  public boolean existsInSet(Class<?> classType, String id) {
    String keySpace = getKeySpace(classType);
    return Boolean.TRUE.equals(setOperations.isMember(keySpace, id));
  }

  private String getKeySpace(Class<?> classType) {
    RedisHash redisHashAnnotation = classType.getAnnotation(RedisHash.class);
    return redisHashAnnotation != null ? redisHashAnnotation.value() : "";
  }
}
