package xyz.fakeplex.api.common.redis.entity.data;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.redis.annotation.RedisString;
import xyz.fakeplex.api.common.redis.type.RedisStringObject;

/**
 * @author Kyle
 */
@Getter
@Setter
@RedisString("data.bungeeServers.ALL")
public class BungeeServer extends RedisStringObject {

  private final String name;
  private String region;
  private int playerCount;
  private String publicAddress;
  private int port;
  private boolean connected;

  public BungeeServer(
      String name,
      String region,
      String publicAddress,
      int port,
      int playerCount,
      boolean connected) {
    super(name);
    this.name = name;
    this.region = region;
    this.playerCount = playerCount;
    this.publicAddress = publicAddress;
    this.port = port;
    this.connected = connected;
  }
}
