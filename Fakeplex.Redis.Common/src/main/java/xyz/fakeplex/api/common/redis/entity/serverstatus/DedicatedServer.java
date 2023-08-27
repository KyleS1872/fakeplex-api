package xyz.fakeplex.api.common.redis.entity.serverstatus;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import xyz.fakeplex.api.common.redis.type.RedisHashObject;

/**
 * @author Kyle
 */
@Getter
@Setter
@RedisHash("serverstatus.dedicated")
public class DedicatedServer extends RedisHashObject {

  @Id private final String name;
  private String publicAddress;
  private String privateAddress;
  private String region;
  private String ram;
  private String cpu;

  public DedicatedServer(
      String name,
      String publicAddress,
      String privateAddress,
      String region,
      String ram,
      String cpu) {
    super(name);
    this.name = name;
    this.publicAddress = publicAddress;
    this.privateAddress = privateAddress;
    this.region = region;
    this.ram = ram;
    this.cpu = cpu;
  }
}
