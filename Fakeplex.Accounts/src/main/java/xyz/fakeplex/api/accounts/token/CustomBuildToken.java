package xyz.fakeplex.api.accounts.token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle
 */
public class CustomBuildToken {

  public int CustomBuildId;

  public String PlayerName;
  public String Name;
  public boolean Active;

  public Integer CustomBuildNumber = 0;

  public String PvpClass = "";

  public String SwordSkill = "";
  public Integer SwordSkillLevel = 0;

  public String AxeSkill = "";
  public Integer AxeSkillLevel = 0;

  public String BowSkill = "";
  public Integer BowSkillLevel = 0;

  public String ClassPassiveASkill = "";
  public Integer ClassPassiveASkillLevel = 0;

  public String ClassPassiveBSkill = "";
  public Integer ClassPassiveBSkillLevel = 0;

  public String GlobalPassiveSkill = "";
  public Integer GlobalPassiveSkillLevel = 0;

  public List<SlotToken> Slots = new ArrayList<>(9);

  public int SkillTokens = 12;
  public int ItemTokens = 1;

  @SuppressWarnings("unused")
  public static class SlotToken {

    public SlotToken() {}

    public SlotToken(String name, String material, int amount) {
      Name = name;
      Material = material;
      Amount = amount;
    }

    public String Name = "";
    public String Material = "";
    public int Amount = 0;
  }
}
