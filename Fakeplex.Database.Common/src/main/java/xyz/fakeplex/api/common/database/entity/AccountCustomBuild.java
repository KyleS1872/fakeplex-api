package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = AccountTables.ACCOUNTCUSTOMBUILD_TABLE_NAME)
public class AccountCustomBuild {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
  @JoinColumn(name = "accountId", nullable = false, referencedColumnName = "id")
  private Account account;

  @Column(name = "className", nullable = false, length = 50)
  private String className;

  @Column(name = "active", nullable = false)
  private Boolean active;

  @Column(name = "buildNumber", nullable = false)
  private Integer buildNumber;

  @Column(name = "pvpClass", nullable = false, length = 50)
  private String pvpClass;

  @Column(name = "swordSkill", nullable = false, length = 50)
  private String swordSkill;

  @Column(name = "swordSkillLevel", nullable = false)
  private Integer swordSkillLevel;

  @Column(name = "axeSkill", nullable = false, length = 50)
  private String axeSkill;

  @Column(name = "axeSkillLevel", nullable = false)
  private Integer axeSkillLevel;

  @Column(name = "bowSkill", nullable = false, length = 50)
  private String bowSkill;

  @Column(name = "bowSkillLevel", nullable = false)
  private Integer bowSkillLevel;

  @Column(name = "classPassiveASkill", nullable = false, length = 50)
  private String classPassiveASkill;

  @Column(name = "classPassiveASkillLevel", nullable = false)
  private Integer classPassiveASkillLevel;

  @Column(name = "classPassiveBSkill", nullable = false, length = 50)
  private String classPassiveBSkill;

  @Column(name = "classPassiveBSkillLevel", nullable = false)
  private Integer classPassiveBSkillLevel;

  @Column(name = "globalPassiveSkill", nullable = false, length = 50)
  private String globalPassiveSkill;

  @Column(name = "globalPassiveSkillLevel", nullable = false)
  private Integer globalPassiveSkillLevel;

  @Column(name = "skillTokens", nullable = false)
  private Integer skillTokens;

  @Column(name = "itemTokens", nullable = false)
  private Integer itemTokens;

  @Column(name = "slot1Name", nullable = false, length = 100)
  private String slot1Name;

  @Column(name = "slot1Material", nullable = false, length = 100)
  private String slot1Material;

  @Column(name = "slot1Amount", nullable = false)
  private Integer slot1Amount;

  @Column(name = "slot2Name", nullable = false, length = 100)
  private String slot2Name;

  @Column(name = "slot2Material", nullable = false, length = 100)
  private String slot2Material;

  @Column(name = "slot2Amount", nullable = false)
  private Integer slot2Amount;

  @Column(name = "slot3Name", nullable = false, length = 100)
  private String slot3Name;

  @Column(name = "slot3Material", nullable = false, length = 100)
  private String slot3Material;

  @Column(name = "slot3Amount", nullable = false)
  private Integer slot3Amount;

  @Column(name = "slot4Name", nullable = false, length = 100)
  private String slot4Name;

  @Column(name = "slot4Material", nullable = false, length = 100)
  private String slot4Material;

  @Column(name = "slot4Amount", nullable = false)
  private Integer slot4Amount;

  @Column(name = "slot5Name", nullable = false, length = 100)
  private String slot5Name;

  @Column(name = "slot5Material", nullable = false, length = 100)
  private String slot5Material;

  @Column(name = "slot5Amount", nullable = false)
  private Integer slot5Amount;

  @Column(name = "slot6Name", nullable = false, length = 100)
  private String slot6Name;

  @Column(name = "slot6Material", nullable = false, length = 100)
  private String slot6Material;

  @Column(name = "slot6Amount", nullable = false)
  private Integer slot6Amount;

  @Column(name = "slot7Name", nullable = false, length = 100)
  private String slot7Name;

  @Column(name = "slot7Material", nullable = false, length = 100)
  private String slot7Material;

  @Column(name = "slot7Amount", nullable = false)
  private Integer slot7Amount;

  @Column(name = "slot8Name", nullable = false, length = 100)
  private String slot8Name;

  @Column(name = "slot8Material", nullable = false, length = 100)
  private String slot8Material;

  @Column(name = "slot8Amount", nullable = false)
  private Integer slot8Amount;

  @Column(name = "slot9Name", nullable = false, length = 100)
  private String slot9Name;

  @Column(name = "slot9Material", nullable = false, length = 100)
  private String slot9Material;

  @Column(name = "slot9Amount", nullable = false)
  private Integer slot9Amount;
}
