package xyz.fakeplex.api.common.util;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author Kyle
 */
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class MinecraftUtils {

  private MinecraftUtils() {
    throw new IllegalStateException("Utility class");
  }

  private static final Pattern VALID_USERNAME = Pattern.compile("[a-zA-Z0-9_]{1,16}");

  public static boolean isValidUsername(String name) {
    return name != null && VALID_USERNAME.matcher(name).find();
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  public static boolean isValidUUID(String uuid) {
    try {
      UUID.fromString(uuid);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}
