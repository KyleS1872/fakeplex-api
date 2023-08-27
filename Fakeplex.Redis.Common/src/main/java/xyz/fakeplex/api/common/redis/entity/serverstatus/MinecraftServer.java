package xyz.fakeplex.api.common.redis.entity.serverstatus;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import xyz.fakeplex.api.common.redis.type.RedisStringObject;

/**
 * @author Kyle
 */
@Getter
@Setter
@RedisHash("serverstatus.minecraft.US")
public class MinecraftServer extends RedisStringObject {

  private String name;
  private String group;
  private String motd;
  private int playerCount;
  private int maxPlayerCount;
  private int tps;
  private int ram;
  private int maxRam;
  private String publicAddress;
  private int port;
  private int donorsOnline;
  private long startUpDate;
  private long currentTime;

  public MinecraftServer(
      String name,
      String group,
      String motd,
      String publicAddress,
      int port,
      int playerCount,
      int maxPlayerCount,
      int tps,
      int ram,
      int maxRam,
      long startUpDate,
      int donorsOnline,
      long currentTime) {
    super(name);
    this.name = name;
    this.group = group;
    this.motd = motd;
    this.playerCount = playerCount;
    this.maxPlayerCount = maxPlayerCount;
    this.tps = tps;
    this.ram = ram;
    this.maxRam = maxRam;
    this.publicAddress = publicAddress;
    this.port = port;
    this.donorsOnline = donorsOnline;
    this.startUpDate = startUpDate;
    this.currentTime = currentTime;
  }
}
