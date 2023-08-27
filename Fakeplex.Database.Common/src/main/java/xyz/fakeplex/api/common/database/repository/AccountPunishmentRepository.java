package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.fakeplex.api.common.database.entity.AccountPunishment;

import java.util.List;

@Repository
public interface AccountPunishmentRepository extends JpaRepository<AccountPunishment, Integer> {

  @Query(nativeQuery = true, value = "SELECT * FROM Account.punishments WHERE target =:accountId")
  List<AccountPunishment> findPunishmentsById(@Param("accountId") int accountId);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "INSERT INTO Account.punishments (target,category,sentence,reason,duration,admin,severity,time) VALUES (:target, :category, :sentence, :reason, :duration, :admin, :severity, :time)")
  void punishPlayerById(
      @Param("target") int target,
      @Param("category") String category,
      @Param("reason") String reason,
      @Param("admin") int admin,
      @Param("time") long time,
      @Param("duration") double duration,
      @Param("sentence") String sentence,
      @Param("severity") int severity);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "UPDATE Account.punishments SET removeReason = :removeReason, removeAdmin = :removeAdmin WHERE id = :punishmentId")
  void removePunishmentById(
      @Param("punishmentId") int punishmentId,
      @Param("removeReason") String removeReason,
      @Param("removeAdmin") int removeAdmin);
}
