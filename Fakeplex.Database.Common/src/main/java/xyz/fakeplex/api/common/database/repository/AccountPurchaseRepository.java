package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.fakeplex.api.common.database.entity.AccountPurchase;

import java.util.List;

@Repository
public interface AccountPurchaseRepository extends JpaRepository<AccountPurchase, Integer> {

  @Query(nativeQuery = true, value = "SELECT * FROM Account.accountPurchases WHERE accountId =:id")
  List<AccountPurchase> findPurchasesByAccountId(@Param("id") Integer id);

  @Query(
      nativeQuery = true,
      value =
          "SELECT * FROM Account.accountPurchases WHERE accountId = :accountId AND salesPackageId = :salesPackageId")
  List<AccountPurchase> getOwnedByPackageId(
      @Param("accountId") Integer accountId, @Param("salesPackageId") int salesPackageId);

  @Query(
      nativeQuery = true,
      value =
          "SELECT * FROM Account.accountPurchases WHERE accountId = :accountId AND salesPackageName = :salesPackageName")
  List<AccountPurchase> getOwnedByPackageName(
      @Param("accountId") Integer accountId, @Param("salesPackageName") String salesPackageName);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "INSERT INTO Account.accountPurchases (accountId, salesPackageId, usingCredits, known) VALUES (:accountId, :salesPackageId, :usingCredits, :known)")
  void insertPurchase(
      @Param("accountId") int accountId,
      @Param("salesPackageId") int salesPackageId,
      @Param("usingCredits") int usingCredits,
      @Param("known") int known);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "INSERT INTO Account.accountPurchases (accountId, salesPackageName, cost, premium, coinPurchase, known) VALUES (:accountId, :salesPackageName, :cost, :premium, :coinPurchase, :known)")
  void insertUnknownPurchase(
      @Param("accountId") int accountId,
      @Param("salesPackageName") String salesPackageName,
      @Param("cost") int cost,
      @Param("premium") int premium,
      @Param("coinPurchase") int coinPurchase,
      @Param("known") int known);
}
