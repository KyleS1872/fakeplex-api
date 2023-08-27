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
@RedisString("data.playerStatus.US")
public class PlayerStatus extends RedisStringObject {

  private final UUID uuid;
  private String name;
  private String server;

  public PlayerStatus(UUID uuid, String name, String server) {
    super(uuid.toString());
    this.uuid = uuid;
    this.name = name;
    this.server = server;
  }
}
