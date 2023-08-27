package xyz.fakeplex.api.common.util;

/**
 * @author Kyle
 */
public class ResourceUrls {

  private ResourceUrls() {
    throw new IllegalStateException("Utility class");
  }

  public static final String BASE_RESOURCE_URI = "/";

  // Accounts
  public static final String ACCOUNTS_PLAYER_ACCOUNT_URI = "PlayerAccount";
  public static final String ACCOUNTS_PETS_URI = "Pets";
  public static final String ACCOUNTS_DOMINATE_URI = "Dominate";

  // Amplifiers
  public static final String AMPLIFIERS_GET_ALL_BOOSTERS_URI = "/booster";
  public static final String AMPLIFIERS_GET_BOOSTERS_URI = "/booster/{boosterGroup}";
  public static final String AMPLIFIERS_POST_BOOSTERS_URI = "/booster/{boosterGroup}";

  // Antispam
  public static final String ANTISPAM_URI_BASE = "/chat";
  public static final String ANTISPAM_URI = "/{source}";

  // Banner
  public static final String BANNER_SERVER_URI = "/api/server";
  public static final String BANNER_BANNER_URI = "/api/banner";

  // Enderchest
  public static final String ENDERCHEST_NEXT_URI = "/map/{mapType}/next";
  public static final String ENDERCHEST_UPLOAD_URI = "/map/{mapType}/upload";

  // ChatFilter
  public static final String CHATFILTER_URI = "/content/item/moderate";
}
