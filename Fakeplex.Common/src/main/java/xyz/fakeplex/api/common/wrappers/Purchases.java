package xyz.fakeplex.api.common.wrappers;

import lombok.Getter;

import java.util.List;

/**
 * @author Kyle
 */
@Getter
public class Purchases {

  private final List<Integer> known;
  private final List<String> unknown;

  public Purchases(List<Integer> known, List<String> unknown) {
    this.known = known;
    this.unknown = unknown;
  }
}
