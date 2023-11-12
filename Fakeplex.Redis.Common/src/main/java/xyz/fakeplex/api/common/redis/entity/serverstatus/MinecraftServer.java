package xyz.fakeplex.api.common.redis.entity.serverstatus;

import com.google.gson.JsonSyntaxException;
import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.redis.annotation.RedisString;
import xyz.fakeplex.api.common.redis.type.RedisStringObject;
import xyz.fakeplex.api.common.redis.util.RedisUtils;

/**
 * @author Kyle
 */
@Getter
@Setter
@RedisString("serverstatus.minecraft.US")
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

  public GameInfo getGameInfo() {
    try {
      return RedisUtils.gson.fromJson(motd, GameInfo.class);
    } catch (JsonSyntaxException e) {
      return null;
    }
  }

  @Getter
  public static class GameInfo {

    private final String game;
    private final String mode;
    private final String map;
    private final int timer;
    private final String[] votingOn;
    private final String hostRank;
    private final GameDisplayStatus status;
    private final GameJoinStatus joinable;

    public GameInfo(
        String game,
        String mode,
        String map,
        int timer,
        String[] votingOn,
        String hostRank,
        GameDisplayStatus status,
        GameJoinStatus joinable) {
      this.game = game;
      this.mode = mode;
      this.map = map;
      this.timer = timer;
      this.votingOn = votingOn;
      this.hostRank = hostRank;
      this.status = status;
      this.joinable = joinable;
    }

    public enum GameDisplayStatus {
      ALWAYS_OPEN,
      STARTING,
      VOTING,
      WAITING,
      IN_PROGRESS,
      CLOSING
    }

    public enum GameJoinStatus {
      OPEN,
      RANKS_ONLY,
      CLOSED
    }
  }
}
