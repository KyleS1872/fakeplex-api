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
@RedisString("data.profileCacheRepo.ALL")
public class PlayerCacheRepo extends RedisStringObject {

  private UUID uuid;
  private final String playerName;
  private String propertyMap;

  public PlayerCacheRepo(UUID uuid, String playerName, String propertyMap) {
    super(playerName);
    this.uuid = uuid;
    this.playerName = playerName;
    this.propertyMap = propertyMap;
  }
}
