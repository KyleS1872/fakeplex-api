package xyz.fakeplex.api.common.redis.type;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author Kyle
 */
@Getter
@RedisHash
public abstract class RedisHashObject implements Serializable, RedisObject {

  private final transient String identifier;

  protected RedisHashObject(String identifier) {
    this.identifier = identifier;
  }
}
