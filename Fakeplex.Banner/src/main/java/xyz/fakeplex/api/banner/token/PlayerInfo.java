package xyz.fakeplex.api.banner.token;

import lombok.Getter;

import java.util.UUID;

/**
 * @author Kyle
 */
@Getter
public class PlayerInfo {

  private final String name;
  private final String realName;
  private final UUID uuid;
  private final int accountId;
  private final String ip;

  public PlayerInfo(String name, String realName, UUID uuid, int accountId, String ip) {
    this.name = name;
    this.realName = realName;
    this.uuid = uuid;
    this.accountId = accountId;
    this.ip = ip;
  }
}
