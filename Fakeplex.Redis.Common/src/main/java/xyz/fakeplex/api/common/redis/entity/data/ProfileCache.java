package xyz.fakeplex.api.common.redis.entity.data;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.redis.annotation.RedisString;
import xyz.fakeplex.api.common.redis.type.RedisStringObject;

import java.util.UUID;

/**
 * @author Kyle
 */
@Getter
@Setter
@RedisString("data.playercache.ALL")
public class ProfileCache extends RedisStringObject {

  private int id;
  private final UUID uuid;
  private String name;
  private long loginTime;
  private int sessionId;
  private int version;

  public ProfileCache(int id, UUID uuid, String name, long loginTime, int sessionId, int version) {
    super(uuid.toString());
    this.id = id;
    this.uuid = uuid;
    this.name = name;
    this.loginTime = loginTime;
    this.sessionId = sessionId;
    this.version = version;
  }
}
