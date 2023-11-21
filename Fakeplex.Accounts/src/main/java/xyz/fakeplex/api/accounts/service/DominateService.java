package xyz.fakeplex.api.accounts.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.fakeplex.api.accounts.token.dominate.SkillToken;
import xyz.fakeplex.api.accounts.token.donation.GameSalesPackageToken;
import xyz.fakeplex.api.common.database.AccountDatabase;
import xyz.fakeplex.api.common.database.entity.GameSalesPackage;
import xyz.fakeplex.api.common.database.entity.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle
 */
@Slf4j
@Service
public class DominateService {

  private final AccountDatabase accountDatabase;

  public DominateService(AccountDatabase accountDatabase) {
    this.accountDatabase = accountDatabase;
  }

  /**
   * Retrieves List of Skill Tokens as Database Entities
   *
   * @param skillTokens List of Skill Token
   * @return List of Skill Token
   */
  public List<SkillToken> getSkills(List<SkillToken> skillTokens) {
    ArrayList<SkillToken> skills = new ArrayList<>();

    for (SkillToken skillToken : skillTokens) {

      Skill accountSkill = accountDatabase.getSkillRepository().findSkillByName(skillToken.Name);

      if (accountSkill == null) skills.add(createSkill(skillToken));
      else skills.add(generateSkillToken(accountSkill));
    }
    return skills;
  }

  /**
   * Creates Skill Token in Database
   *
   * @param skillToken Skill Token
   * @return Skill Token
   */
  private SkillToken createSkill(SkillToken skillToken) {
    GameSalesPackage gameSalesPackage = new GameSalesPackage();
    gameSalesPackage.setGems(0);
    gameSalesPackage.setEconomy(0);
    gameSalesPackage.setFree(false);
    accountDatabase.getGameSalesPackageRepository().save(gameSalesPackage);

    Skill skill = new Skill();
    skill.setName(skillToken.Name);
    skill.setLevel(skillToken.Level);
    skill.setGameSalesPackage(gameSalesPackage);
    accountDatabase.getSkillRepository().save(skill);

    return generateSkillToken(skill);
  }

  /**
   * Generate Skill Token from Database Object
   *
   * @param skill Database Object
   * @return Token Object
   */
  private SkillToken generateSkillToken(Skill skill) {
    SkillToken skillToken = new SkillToken();
    skillToken.SkillId = skill.getId();
    skillToken.Name = skill.getName();
    skillToken.Level = skill.getLevel();

    GameSalesPackage gameSalesPackage = skill.getGameSalesPackage();

    GameSalesPackageToken gameSalesPackageToken = new GameSalesPackageToken();
    gameSalesPackageToken.Gems = gameSalesPackage.getGems();
    gameSalesPackageToken.Free = gameSalesPackage.getFree();
    gameSalesPackageToken.GameSalesPackageId = gameSalesPackage.getId();

    skillToken.SalesPackage = gameSalesPackageToken;
    return skillToken;
  }
}
