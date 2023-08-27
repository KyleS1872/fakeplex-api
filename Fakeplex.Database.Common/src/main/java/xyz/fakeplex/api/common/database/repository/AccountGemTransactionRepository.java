package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.fakeplex.api.common.database.entity.AccountGemTransaction;

@Repository
public interface AccountGemTransactionRepository
    extends JpaRepository<AccountGemTransaction, Integer> {

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "INSERT INTO Account.accountGemTransactions (accountId,reason,gems) VALUES (:accountId, :reason, :amount)")
  void addTransaction(
      @Param("accountId") int accountId,
      @Param("reason") String reason,
      @Param("amount") int amount);
}
