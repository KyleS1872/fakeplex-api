package xyz.fakeplex.api.common.redis.type;

import com.google.gson.Gson;
import lombok.Getter;
import xyz.fakeplex.api.common.redis.annotation.RedisString;

import java.io.Serializable;

/**
 * @author Kyle
 */
@Getter
@RedisString
public abstract class RedisStringObject implements Serializable, RedisObject {

  private final transient String identifier;

  protected RedisStringObject(String identifier) {
    this.identifier = identifier;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
