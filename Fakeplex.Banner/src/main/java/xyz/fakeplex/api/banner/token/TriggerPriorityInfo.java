package xyz.fakeplex.api.banner.token;

import lombok.Getter;

/**
 * @author Kyle
 */
@Getter
public class TriggerPriorityInfo {

  private final PlayerInfo target;
  private final PriorityCause cause;

  public TriggerPriorityInfo(PlayerInfo target, PriorityCause cause) {
    this.target = target;
    this.cause = cause;
  }
}
