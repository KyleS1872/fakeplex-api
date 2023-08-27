package xyz.fakeplex.api.common.redis.entity.data;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.redis.annotation.RedisString;
import xyz.fakeplex.api.common.redis.type.RedisStringObject;

import java.util.Date;

/**
 * @author Kyle
 */
@Getter
@Setter
@RedisString("data.GemTransfers.US")
public class GemTransfer extends RedisStringObject {

  private final String playerName;
  private Date date;

  public GemTransfer(String playerName, Date date) {
    super(playerName);
    this.playerName = playerName;
    this.date = date;
  }
}
