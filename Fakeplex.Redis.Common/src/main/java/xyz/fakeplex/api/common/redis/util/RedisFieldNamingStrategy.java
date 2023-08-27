package xyz.fakeplex.api.common.redis.util;

import com.google.gson.FieldNamingStrategy;

import java.lang.reflect.Field;

/**
 * @author Kyle
 */
public class RedisFieldNamingStrategy implements FieldNamingStrategy {
  @Override
  public String translateName(Field field) {
    return "_" + field.getName();
  }
}
