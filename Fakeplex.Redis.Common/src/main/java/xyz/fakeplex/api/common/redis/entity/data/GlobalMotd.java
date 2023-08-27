package xyz.fakeplex.api.common.redis.entity.data;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.redis.annotation.RedisString;
import xyz.fakeplex.api.common.redis.type.RedisStringObject;

import java.util.List;

/**
 * @author Kyle
 */
@Getter
@Setter
@RedisString("data.globalMotd.ALL")
public class GlobalMotd extends RedisStringObject {

  private final String name;
  private String heading;
  private List<String> motd;

  public GlobalMotd(String name, String heading, List<String> motd) {
    super(name);
    this.name = name;
    this.heading = heading;
    this.motd = motd;
  }
}
