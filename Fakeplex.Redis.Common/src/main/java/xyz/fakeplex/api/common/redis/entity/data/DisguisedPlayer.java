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
@RedisString("data.disguisedPlayer.ALL")
public class DisguisedPlayer extends RedisStringObject {

  private final int accountID;
  private final String playerName;
  private String disguisedPlayer;
  private String disguisedSkin;
  private String serializedGameProfile;

  public DisguisedPlayer(
      int accountID,
      String playerName,
      String disguisedPlayer,
      String disguisedSkin,
      String gameProfile) {
    super(accountID + playerName);
    this.accountID = accountID;
    this.playerName = playerName;
    this.disguisedPlayer = disguisedPlayer;
    this.disguisedSkin = disguisedSkin;
    serializedGameProfile = gameProfile;
  }
}
