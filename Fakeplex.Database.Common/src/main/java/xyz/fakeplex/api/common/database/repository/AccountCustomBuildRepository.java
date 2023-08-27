package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.fakeplex.api.common.database.entity.AccountCustomBuild;

import java.util.List;

@Repository
public interface AccountCustomBuildRepository extends JpaRepository<AccountCustomBuild, Integer> {

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM Account.accountCustomBuilds WHERE accountId =:accountId")
  List<AccountCustomBuild> getCustomBuilds(@Param("accountId") int accountId);

  @Query(
      nativeQuery = true,
      value =
          "SELECT * FROM Account.accountCustomBuilds WHERE accountId = :accountId AND buildNumber = :buildNumber AND pvpClass = :pvpClass LIMIT 1")
  AccountCustomBuild getCustomBuild(
      @Param("accountId") int accountId,
      @Param("buildNumber") int buildNumber,
      @Param("pvpClass") String pvpClass);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "INSERT INTO Account.accountCustomBuilds (accountId, className,  active, "
              + "buildNumber, pvpClass, swordSkill, swordSkillLevel, axeSkill, axeSkillLevel, bowSkill, bowSkillLevel, "
              + "classPassiveASkill, classPassiveASkillLevel, classPassiveBSkill, classPassiveBSkillLevel, "
              + "globalPassiveSkill, globalPassiveSkillLevel, skillTokens, itemTokens, "
              + "slot1Name, slot1Material, slot1Amount, slot2Name, slot2Material, slot2Amount, slot3Name, slot3Material, slot3Amount, "
              + "slot4Name, slot4Material, slot4Amount, slot5Name, slot5Material, slot5Amount, slot6Name, slot6Material, slot6Amount, "
              + "slot7Name, slot7Material, slot7Amount, slot8Name, slot8Material, slot8Amount, slot9Name, slot9Material, slot9Amount) VALUES (:accountId, :className, :active, "
              + ":buildNumber, :pvpClass, :swordSkill, :swordSkillLevel, :axeSkill, :axeSkillLevel, :bowSkill, :bowSkillLevel, "
              + ":classPassiveASkill, :classPassiveASkillLevel, :classPassiveBSkill, :classPassiveBSkillLevel, "
              + ":globalPassiveSkill, :globalPassiveSkillLevel, :skillTokens, :itemTokens, "
              + ":slot1Name, :slot1Material, :slot1Amount, :slot2Name, :slot2Material, :slot2Amount, :slot3Name, :slot3Material, :slot3Amount, "
              + ":slot4Name, :slot4Material, :slot4Amount, :slot5Name, :slot5Material, :slot5Amount, :slot6Name, :slot6Material, :slot6Amount, "
              + ":slot7Name, :slot7Material, :slot7Amount, :slot8Name, :slot8Material, :slot8Amount, :slot9Name, :slot9Material, :slot9Amount)")
  void insertCustomBuild(
      @Param("accountId") int accountId,
      @Param("className") String className,
      @Param("active") boolean active,
      @Param("buildNumber") int buildNumber,
      @Param("pvpClass") String pvpClass,
      @Param("swordSkill") String swordSkill,
      @Param("swordSkillLevel") int swordSkillLevel,
      @Param("axeSkill") String axeSkill,
      @Param("axeSkillLevel") int axeSkillLevel,
      @Param("bowSkill") String bowSkill,
      @Param("bowSkillLevel") int bowSkillLevel,
      @Param("classPassiveASkill") String classPassiveASkill,
      @Param("classPassiveASkillLevel") int classPassiveASkillLevel,
      @Param("classPassiveBSkill") String classPassiveBSkill,
      @Param("classPassiveBSkillLevel") int classPassiveBSkillLevel,
      @Param("globalPassiveSkill") String globalPassiveSkill,
      @Param("globalPassiveSkillLevel") int globalPassiveSkillLevel,
      @Param("skillTokens") int skillTokens,
      @Param("itemTokens") int itemTokens,
      @Param("slot1Name") String slot1Name,
      @Param("slot1Material") String slot1Material,
      @Param("slot1Amount") int slot1Amount,
      @Param("slot2Name") String slot2Name,
      @Param("slot2Material") String slot2Material,
      @Param("slot2Amount") int slot2Amount,
      @Param("slot3Name") String slot3Name,
      @Param("slot3Material") String slot3Material,
      @Param("slot3Amount") int slot3Amount,
      @Param("slot4Name") String slot4Name,
      @Param("slot4Material") String slot4Material,
      @Param("slot4Amount") int slot4Amount,
      @Param("slot5Name") String slot5Name,
      @Param("slot5Material") String slot5Material,
      @Param("slot5Amount") int slot5Amount,
      @Param("slot6Name") String slot6Name,
      @Param("slot6Material") String slot6Material,
      @Param("slot6Amount") int slot6Amount,
      @Param("slot7Name") String slot7Name,
      @Param("slot7Material") String slot7Material,
      @Param("slot7Amount") int slot7Amount,
      @Param("slot8Name") String slot8Name,
      @Param("slot8Material") String slot8Material,
      @Param("slot8Amount") int slot8Amount,
      @Param("slot9Name") String slot9Name,
      @Param("slot9Material") String slot9Material,
      @Param("slot9Amount") int slot9Amount);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "UPDATE Account.accountCustomBuilds SET className = :className, active = :active, "
              + "swordSkill = :swordSkill, swordSkillLevel = :swordSkillLevel, axeSkill = :axeSkill, axeSkillLevel = :axeSkillLevel, bowSkill = :bowSkill, bowSkillLevel = :bowSkillLevel, "
              + "classPassiveASkill = :classPassiveASkill, classPassiveASkillLevel = :classPassiveASkillLevel, classPassiveBSkill = :classPassiveBSkill, classPassiveBSkillLevel = :classPassiveBSkillLevel, "
              + "globalPassiveSkill = :globalPassiveSkill, globalPassiveSkillLevel = :globalPassiveSkillLevel, skillTokens = :skillTokens, itemTokens = :itemTokens, "
              + "slot1Name = :slot1Name, slot1Material = :slot1Material, slot1Amount = :slot1Amount, slot2Name = :slot2Name, slot2Material = :slot2Material, slot2Amount = :slot2Amount, "
              + "slot3Name = :slot3Name, slot3Material = :slot3Material, slot3Amount = :slot3Amount, slot4Name = :slot4Name, slot4Material = :slot4Material, slot4Amount = :slot4Amount, "
              + "slot5Name = :slot5Name, slot5Material = :slot5Material, slot5Amount = :slot5Amount, slot6Name = :slot6Name, slot6Material = :slot6Material, slot6Amount = :slot6Amount, "
              + "slot7Name = :slot7Name, slot7Material = :slot7Material, slot7Amount = :slot7Amount, slot8Name = :slot8Name, slot8Material = :slot8Material, slot8Amount = :slot8Amount, "
              + "slot9Name = :slot9Name, slot9Material = :slot9Material, slot9Amount = :slot9Amount "
              + "WHERE accountId = :accountId AND buildNumber = :buildNumber AND pvpClass = :pvpClass")
  void updateCustomBuild(
      @Param("accountId") int accountId,
      @Param("className") String className,
      @Param("active") boolean active,
      @Param("buildNumber") int buildNumber,
      @Param("pvpClass") String pvpClass,
      @Param("swordSkill") String swordSkill,
      @Param("swordSkillLevel") int swordSkillLevel,
      @Param("axeSkill") String axeSkill,
      @Param("axeSkillLevel") int axeSkillLevel,
      @Param("bowSkill") String bowSkill,
      @Param("bowSkillLevel") int bowSkillLevel,
      @Param("classPassiveASkill") String classPassiveASkill,
      @Param("classPassiveASkillLevel") int classPassiveASkillLevel,
      @Param("classPassiveBSkill") String classPassiveBSkill,
      @Param("classPassiveBSkillLevel") int classPassiveBSkillLevel,
      @Param("globalPassiveSkill") String globalPassiveSkill,
      @Param("globalPassiveSkillLevel") int globalPassiveSkillLevel,
      @Param("skillTokens") int skillTokens,
      @Param("itemTokens") int itemTokens,
      @Param("slot1Name") String slot1Name,
      @Param("slot1Material") String slot1Material,
      @Param("slot1Amount") int slot1Amount,
      @Param("slot2Name") String slot2Name,
      @Param("slot2Material") String slot2Material,
      @Param("slot2Amount") int slot2Amount,
      @Param("slot3Name") String slot3Name,
      @Param("slot3Material") String slot3Material,
      @Param("slot3Amount") int slot3Amount,
      @Param("slot4Name") String slot4Name,
      @Param("slot4Material") String slot4Material,
      @Param("slot4Amount") int slot4Amount,
      @Param("slot5Name") String slot5Name,
      @Param("slot5Material") String slot5Material,
      @Param("slot5Amount") int slot5Amount,
      @Param("slot6Name") String slot6Name,
      @Param("slot6Material") String slot6Material,
      @Param("slot6Amount") int slot6Amount,
      @Param("slot7Name") String slot7Name,
      @Param("slot7Material") String slot7Material,
      @Param("slot7Amount") int slot7Amount,
      @Param("slot8Name") String slot8Name,
      @Param("slot8Material") String slot8Material,
      @Param("slot8Amount") int slot8Amount,
      @Param("slot9Name") String slot9Name,
      @Param("slot9Material") String slot9Material,
      @Param("slot9Amount") int slot9Amount);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = "DELETE FROM Account.accountCustomBuilds WHERE id = :customBuildId")
  void deleteCustomBuild(@Param("customBuildId") int customBuildId);
}
