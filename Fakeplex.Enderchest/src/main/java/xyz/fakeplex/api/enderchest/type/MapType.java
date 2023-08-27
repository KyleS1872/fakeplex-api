package xyz.fakeplex.api.enderchest.type;

import lombok.Getter;

/**
 * @author Kyle
 */
public enum MapType {

  // Misc
  None("None"),
  Other("Other"),
  Unknown("Unknown"),

  // Dedicated
  Lobby("Lobby"),
  Clans("Clans"),
  GemHunters("Gem Hunters"),

  // Games
  UHC("UHC", "Ultra Hardcore"),
  UHCSolo("Ultra Hardcore Solo", UHC.getFolderName()),

  CastleSiege("Castle Siege"),
  CastleAssault("Castle Assault"),
  CastleAssaultTDM("Castle Assault TDM"),
  ChampionsTDM("Champions TDM"),
  ChampionsDominate("Champions Domination"),
  ChampionsCTF("Champions CTF"),

  BaconBrawl("Bacon Brawl"),
  Barbarians("A Barbarians Life"),
  Bridge("The Bridges"),
  Build("Master Builders"),
  DeathTag("Death Tag"),
  DragonEscape("Dragon Escape"),
  DragonEscapeTeams("Dragon Escape Teams"),
  Dragons("Dragons"),
  Gladiators("Gladiators"),
  HideSeek("Block Hunt"),
  Lobbers("Bomb Lobbers"),
  Micro("Micro Battle"),
  Runner("Runner"),
  Sheep("Sheep Quest"),
  Snake("Snake"),
  TurfWars("Turf Wars"),

  Quiver("One in the Quiver"),
  QuiverTeams("One in the Quiver Teams"),

  CakeWars("Cake Wars Solo"),
  CakeWars2("Cake Wars Duos"),
  CakeWars4("Cake Wars Standard"),

  Skywars("Skywars"),
  SkywarsTeams("Skywars Teams", Skywars.getFolderName());

  @Getter private final String categoryName;

  private final String folderName;

  MapType(String categoryName) {
    this(categoryName, null);
  }

  MapType(String categoryName, String folderName) {
    this.categoryName = categoryName;
    this.folderName = folderName;
  }

  public String getFolderName() {
    if (folderName == null) return categoryName != null ? categoryName : Unknown.categoryName;

    return folderName;
  }

  public static MapType getEnum(String value) {
    for (MapType v : values()) if (v.getCategoryName().equalsIgnoreCase(value)) return v;

    for (MapType v : values()) if (v.getFolderName().equalsIgnoreCase(value)) return v;

    return Unknown;
  }
}
