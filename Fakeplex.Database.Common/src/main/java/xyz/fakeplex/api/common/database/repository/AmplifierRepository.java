package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.fakeplex.api.common.database.entity.Amplifier;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AmplifierRepository extends JpaRepository<Amplifier, Integer> {

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "INSERT INTO Account.amplifiers (name, uuid, accountId, boosterGroup, duration, multiplier, startTime, endTime, activationTime) VALUES(:playerName, :uuid, :accountId, :boosterGroup, :duration, :multiplier, :startTime, :endTime, :activationTime)")
  Integer insertBooster(
      @Param("playerName") String playerName,
      @Param("uuid") String uuid,
      @Param("accountId") int accountId,
      @Param("boosterGroup") String boosterGroup,
      @Param("duration") int duration,
      @Param("multiplier") double multiplier,
      @Param("startTime") Timestamp startTime,
      @Param("endTime") Timestamp endTime,
      @Param("activationTime") Timestamp activationTime);

  @Query(nativeQuery = true, value = "SELECT * FROM Account.amplifiers WHERE endTime > NOW()")
  List<Amplifier> getActiveBoosters();

  @Query(
      nativeQuery = true,
      value =
          "SELECT * FROM Account.amplifiers WHERE boosterGroup = :boosterGroup AND endTime > NOW()")
  List<Amplifier> getActiveBoosters(@Param("boosterGroup") String boosterGroup);

  @Query(
      nativeQuery = true,
      value =
          "SELECT endTime FROM Account.amplifiers WHERE boosterGroup = :boosterGroup AND endTime > NOW() ORDER BY endTime DESC LIMIT 1")
  Timestamp getLatestBoosterEnd(@Param("boosterGroup") String boosterGroup);
}
